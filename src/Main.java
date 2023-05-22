import exception.CIllegalArgumentException;
import exception.CInsuranceNotFoundException;
import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceListImpl;
import insurance.InsuranceState;
import insurance.InsuranceType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

import Reward.Reward;
import Teams.InsuranceDevelopmentTeam;
import Teams.RewardTeam;
import exception.CustomException;
import java.util.stream.Collectors;
import outerActor.OuterActor;
import util.Constants;
import util.Constants.Crud;
import util.Constants.Target;
import util.TuiReader;

public class Main {

    private static InsuranceDevelopmentTeam insuranceDevelopmentTeam;
    private static InsuranceList insuranceList;

    public static void initialize() {
        insuranceList = new InsuranceListImpl();
        insuranceDevelopmentTeam = new InsuranceDevelopmentTeam(insuranceList);

    }
    public static void main(String[] args) {
        initialize();
        while (true) {
            try {
                printMenu();
                int choice = TuiReader.choice();
                switch (choice) {
                    case 1:
                        createInsurance();
                        break;
                    case 8:
                        processReward();
                        break;
                    case 0:
                        System.out.close();
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void printMenu() {
        System.out.println("********************* MENU *********************");
        System.out.println(" 1. 상품개발");
        System.out.println(" 8. 보상 처리");
        System.out.println(" 0. 종료 ");
    }
    private static void createInsurance() {
        System.out.println("********************* 상품 개발 *********************");
        System.out.println(" 1. 상품 기획 버튼");
        System.out.println(" 2. 상품 개발 버튼");
        System.out.println(" 3. 상품 인가 버튼");
        int choice = TuiReader.choice();
        switch (choice) {
            case 1:
                createInsurancePlan();
                break;
            case 2:
                Insurance insurance = designInsurance();
                estimateProfit(insurance);
                insurance.setInsuranceState(InsuranceState.DESIGNED);
                insuranceList.createDesign(insurance);
                System.out.println("저장 완료되었습니다. 요율 분석 버튼을 눌러 자동으로 이동합니다.");
                analyzeInsuranceRate(insurance);
                insuranceList.updateRate(insurance.getRate());
                System.out.println("해당 상품 설계를 완료했습니다.");
                break;
            case 3:
                authorizeInsurance();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    private static void createInsurancePlan() {
        System.out.println("********************* 상품 기획 *********************");
        System.out.println(" 1. 새상품 기획 버튼");
        System.out.println(" 2. 기존 상품 관리 버튼");
        int choice = TuiReader.choice();
        switch (choice) {
            case 1:
                System.out.println("고객니즈 및 경쟁사의 동향 분석 보고서를 작성하세요: ");
                insuranceDevelopmentTeam.plan(Target.INSURANCE, Crud.CREATE);
                System.out.println("새 상품 기획안을 저장하였습니다.");
                break;
            case 2:
                System.out.println("********************* 보험 기획안 *********************");
                List<Insurance> insurances = insuranceList.getAllInsurance()
                    .stream()
                    .filter(insurance -> insurance.getInsuranceState() == InsuranceState.PLANED)
                    .collect(Collectors.toList());
                if (insurances.size() == 0) {
                    throw new CInsuranceNotFoundException("수정하거나 삭제할 보험 기획안이 없습니다.");
                }
                System.out.println("수정하거나 삭제할 보험 기획안의 번호를 입력해주세요.");
                for (int i = 0; i < insurances.size(); i++) {
                    System.out.println(i + ". " + insurances.get(i).getPlanReport());
                }
                int choice2 = TuiReader.choice();
                while (choice2 < 0 || choice2 >= insurances.size()) {
                    System.out.println("정확히 선택해주세요.");
                    choice2 = TuiReader.choice();
                }
                System.out.println("수정: 1, 삭제: 2");
                int choice3 = TuiReader.choice();
                while (choice3 != 1 && choice3 != 2) {
                    System.out.println("정확히 선택해주세요.");
                    choice3 = TuiReader.choice();
                }
                if (choice3 == 1) {
                    System.out.println("기존 기획안: " + insurances.get(choice2).getPlanReport());
                    System.out.println("수정할 기획안을 입력해주세요.");
                    String report = TuiReader.readInput("보고서가 올바른 형식이 아닙니다.");
                    insuranceList.updateReport(insurances.get(choice2).getInsuranceID(), report);
                    System.out.println("상품 기획안을 수정하였습니다.");
                } else if (choice3 == 2) {
                    insuranceList.delete(insurances.get(choice2).getInsuranceID());
                    System.out.println("해당 상품 기획안을 삭제하였습니다.");
                }
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
    private static Insurance designInsurance() {
        List<Insurance> insurances = insuranceList.getAllInsurance()
            .stream().filter(insurance -> insurance.getInsuranceState() == InsuranceState.PLANED)
            .collect(Collectors.toList());
        if (insurances.size() == 0) {
            throw new CInsuranceNotFoundException("설계할 보험 기획안이 없습니다.");
        }
        System.out.println("********************* 상품 개발 *********************");
        System.out.println("설계할 보험 기획안의 번호를 입력해주세요.");
        for (int i = 0; i < insurances.size(); i++) {
            System.out.println(i + ". " + insurances.get(i).getPlanReport());
        }
        int choiceId = TuiReader.choice();
        while (choiceId < 0 || choiceId >= insurances.size()) {
            System.out.println("정확히 선택해주세요.");
            choiceId = TuiReader.choice();
        }
        System.out.println("설계할 보험 기획안: " + insurances.get(choiceId).getPlanReport());
        System.out.println(
            "설계할 보험의 이름, 상품 종류, 판매 대상, 가입 조건, 보험료, 보장 내용, 개발 예상 비용을 / 로 구분하여 입력해주세요.");
        boolean isSuccessInput = false;
        Insurance insurance = null;
        while (!isSuccessInput) {
            try {
                String[] input = TuiReader.readInput("다시 입력하세요.").split("/");
                if (input.length != 7) {
                    throw new CIllegalArgumentException("다시 입력하세요");
                }
                insurance = insurances.get(choiceId);
                insurance.setInsuranceName(input[0]);
                insurance.setInsuranceType(InsuranceType.valueOf(input[1]));
                insurance.setSalesTarget(input[2]);
                insurance.setCanRegistTarget(input[3]);
                insurance.setPayment(Integer.parseInt(input[4]));
                insurance.setGuarantee(input[5]);
                insurance.setEstimatedDevelopment(Integer.parseInt(input[6]));
                isSuccessInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
//                e.printStackTrace();로 에러 메시지를 확인할 수 있음
            }
        }
        return insurance;
    }
    private static void estimateProfit(Insurance insurance) {
        System.out.println("********************* 예상 손익 분석 *********************");
        System.out.println(" 예상 손익 분석을 원하시면 1, 아니면 2를 입력하세요.");
        int choice = TuiReader.choice();
        while (choice != 1 && choice != 2) {
            System.out.println("정확히 선택해주세요.");
            choice = TuiReader.choice();
        }
        if (choice == 1) {
            System.out.println("예상 손익률 분석값을 입력해주세요.");
            boolean isSuccessInput = false;
            while (!isSuccessInput) {
                try {
                    String input = TuiReader.readInput("다시 입력하세요.");
                    insurance.setEstimatedProfitRate(Float.parseFloat(input));
                    isSuccessInput = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (choice == 2) {
            System.out.println("예상 손익 분석을 건너뛰었습니다.");
        }
    }
    private static void analyzeInsuranceRate(Insurance insurance) {
        System.out.println("********************* 요율 분석 *********************");
        System.out.println("위험도를 입력해주세요. 입력시 자동으로 요율 검증이 진행됩니다.");
        boolean isSuccessInput = false;
        while (!isSuccessInput) {
            try {
                String input = TuiReader.readInput("다시 입력하세요.");
                insurance.setRiskDegree(Integer.parseInt(input));
                isSuccessInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        float rate = OuterActor.calcInsuranceRate(insurance.getPayment(), insurance.getRiskDegree());
        insurance.setRate(rate);
        System.out.println("검수인이 검수한 결과 " + rate * 100 + "%가 산출되었습니다.");
    }
    private static void authorizeInsurance() {
        System.out.println("********************* 상품 인가 *********************");
        System.out.println("인가할 보험의 번호를 입력해주세요.");
        List<Insurance> insurances = insuranceList.getAllInsurance()
            .stream().filter(insurance -> insurance.getInsuranceState() == InsuranceState.DESIGNED)
            .collect(Collectors.toList());
        if (insurances.size() == 0) {
            throw new CInsuranceNotFoundException("인가할 보험이 없습니다.");
        }
        for (int i = 0; i < insurances.size(); i++) {
            System.out.println(i + ". " + insurances.get(i).getInsuranceName());
        }
        int choice = TuiReader.choice();
        while (choice < 0 || choice >= insurances.size()) {
            System.out.println("정확히 선택해주세요.");
            choice = TuiReader.choice();
        }
        Insurance insurance = insurances.get(choice);
        LocalDateTime authorizedDate = OuterActor.authorizedInsurance(insurance);
        if (insurance.getInsuranceState() == InsuranceState.AUTHORIZED) {
            insuranceList.updateState(insurance.getInsuranceID(), InsuranceState.AUTHORIZED);
            System.out.println(
                authorizedDate.getMonth() + "월 " + authorizedDate.getDayOfMonth() + "일에 합격 되었습니다");
        } else {
            System.out.println("불합격 되었습니다.");
        }
    }
    private static void processReward() {
        RewardTeam rewardTeam = new RewardTeam();
        Vector<Reward> rewardList = rewardTeam.getAllReward();
        if (rewardList.size() == 0) {
            System.out.println("접수된 보상 요청이 없습니다");
        } else {
            System.out.println("------------------------------");
            for (int i = 0; i < rewardList.size(); i++) {
                System.out.println(rewardList.get(i).getCustomerName() + ": " +
                    rewardList.get(i).getAppliResult().getString() +
                    " " + rewardList.get(i).getContent() +
                    " " + Integer.toString(rewardList.get(i).getAppliDate().getMonth()) + "월 " +
                    Integer.toString(rewardList.get(i).getAppliDate().getDay()) + "일 ");
            }
            System.out.println("------------------------------");
            int select = TuiReader.choice();
            Reward selectedReward = rewardList.get(select);
            System.out.println(
                selectedReward.getCustomerName() + " - " + selectedReward.getContent());
            System.out.println("해당 보험에 대해 승인하시겠습니까?");
            System.out.println("1. 보상 지급   2. 요청 거절");
            int resultSelect = TuiReader.choice();
            if (resultSelect == 1) {
                // 승인
                System.out.println(selectedReward.getCustomerName());
                System.out.println("지급 금액: " + Integer.toString(selectedReward.getReward()) + "원");
                System.out.println("책임자 이름: 사용자");
                System.out.println("내용: " + selectedReward.getContent());
                System.out.println("--------- 해당 요청에 대한 보상을 지급합니다. -----------");

                rewardTeam.rewardResult(selectedReward.getRewardID(), Constants.Result.ACCEPT);
            } else if (resultSelect == 2) {
                // Alter2
                System.out.print("보상 거절 사유를 입력하세요: ");
                String reason = TuiReader.readInput("정확한 사유를 입력하세요");
                selectedReward.setContent(reason);        // DENY된 보험 신청의 경우, Content에 거절 사유를 남긴다.
                rewardTeam.rewardResult(selectedReward.getRewardID(), Constants.Result.DENY);
            } else {
                //
            }
        }
    }
}
