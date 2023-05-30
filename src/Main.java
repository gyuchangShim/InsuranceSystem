import business.OperationPolicy;
import business.OperationPolicyList;
import business.OperationPolicyListImpl;
import business.SellGroup;
import business.SellGroupList;
import business.SellGroupListImpl;
import businessEducation.Education;
import businessEducation.EducationStudent;
import contract.*;
import contractManagement.ContractManagementPolicy;
import contractManagement.ContractManagementPolicyList;
import contractManagement.ContractManagementPolicyListImpl;
import customer.CounselingState;
import customer.Customer;
import customer.CustomerCounseling;
import customer.CustomerCounselingList;
import customer.CustomerCounselingListImpl;
import customer.CustomerListImpl;
import customerManagement.CustomerManagement;
import customerManagement.CustomerManagementList;
import customerManagement.CustomerManagementListImpl;
import dao.InsuranceDao;
import exception.CCounselingNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import customer.CustomerList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import marketingPlanning.CampaignProgram;
import marketingPlanning.CampaignProgramList;
import marketingPlanning.CampaignProgramListImpl;
import marketingPlanning.CampaignState;
import reward.Reward;
import teams.BusinessTeam;
import teams.CustomerManagementTeam;
import teams.MarketingPlanningTeam;
import teams.SellGroupTeam;
import teams.UnderwritingTeam;
import undewriting.AssumePolicy;
import exception.CIllegalArgumentException;
import exception.CInsuranceNotFoundException;
import exception.CustomException;
import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceState;
import insurance.InsuranceType;
import outerActor.OuterActor;
import teams.BusinessEducationTeam;
import teams.ContractManagementTeam;
import teams.InsuranceDevelopmentTeam;

import undewriting.AssumePolicyList;
import undewriting.AssumePolicyListImpl;
import userPersona.UserPersona;
import userPersona.UserPersonaList;
import userPersona.UserPersonaListImpl;
import util.Constants;
import util.Constants.Crud;
import util.Constants.Gender;
import util.Constants.Result;
import util.Constants.Target;
import util.TuiReader;

public class Main {
    private static InsuranceList insuranceList;
    private static AssumePolicyList assumePolicyList;
    private static CampaignProgramList campaignProgramList;
    private static ContractList contractList;
    private static CustomerList customerList;
    private static UserPersonaList userPersonaList;
    private static CustomerCounselingList customerCounselingList;
    private static CustomerManagementList customerManagementList;
    private static SellGroupList sellGroupList;
    private static PaymentList paymentList;
    private static OperationPolicyList operationPolicyList;
    private static AdviceNoteList adviceNoteList;
    private static ContractManagementPolicyList contractManagementPolicyList;

    private static InsuranceDevelopmentTeam insuranceDevelopmentTeam;
    private static UnderwritingTeam underwritingTeam;
    private static MarketingPlanningTeam marketingPlanningTeam;
    private static CustomerManagementTeam customerManagementTeam;
    private static SellGroupTeam sellGroupTeam;
    private static BusinessTeam businessTeam;
    private static BusinessEducationTeam businessEducationTeam;
    private static ContractManagementTeam contractManagementTeam;
    private static int customerID;

    public static void initialize() {
        insuranceList = new InsuranceDao();
        assumePolicyList = new AssumePolicyListImpl();
        campaignProgramList = new CampaignProgramListImpl();
        contractList = new ContractListImpl();
        userPersonaList = new UserPersonaListImpl();
        customerList = new CustomerListImpl();
        customerCounselingList = new CustomerCounselingListImpl();
        customerManagementList = new CustomerManagementListImpl();
        operationPolicyList = new OperationPolicyListImpl();
        sellGroupList = new SellGroupListImpl();
        paymentList = new PaymentListImpl();
        adviceNoteList = new AdviceNoteListImpl();
        contractManagementPolicyList = new ContractManagementPolicyListImpl();

        insuranceDevelopmentTeam = new InsuranceDevelopmentTeam(insuranceList);
        marketingPlanningTeam = new MarketingPlanningTeam(campaignProgramList);
        underwritingTeam = new UnderwritingTeam(assumePolicyList);
        customerManagementTeam = new CustomerManagementTeam(customerManagementList, customerList);
        sellGroupTeam = new SellGroupTeam(sellGroupList, insuranceList);
        businessTeam = new BusinessTeam(operationPolicyList, sellGroupList);
        businessEducationTeam = new BusinessEducationTeam();
        contractManagementTeam = new ContractManagementTeam(contractList, insuranceList, customerList,
            contractManagementPolicyList, paymentList, adviceNoteList);
    }
    public static void main(String[] args) {
        initialize();
//        initData();
        while (true) {
            try {
                loginPage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void initData() {
        // 고객 생성
        Customer customer = new Customer();
        customer.setName("엄준식");
        int customerId = customerList.add(customer);
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.setID("test");
        customerManagement.setPW("test");
        customerManagement.setCustomerID(customerId);
        customerManagementList.add(customerManagement);
        // 상담 생성
        CustomerCounseling customerCounseling = new CustomerCounseling();
        customerCounseling.setCustomerId(customerId);
        customerCounseling.setCounselingPlace("서울시 강남구");
        customerCounseling.setCounselingState(CounselingState.APPLIED);
        customerCounseling.setCounselingTime(LocalDateTime.now());
        customerCounselingList.add(customerCounseling);
        // 인가 완료된 보험 생성
        for (int i = 0; i < 3; i++) {
            Insurance insurance = new Insurance();
            insurance.setInsuranceName("살아있는보험" + i);
            insurance.setInsuranceState(InsuranceState.AUTHORIZED);
            insuranceList.add(insurance);
        }
    }
    private static void loginPage(){
        System.out.println("*********************  로그인  *********************");
        System.out.println(" 1. 고객 로그인");
        System.out.println(" 2. 회원가입");
        System.out.println(" 3. 직원 로그인");

        int choice = TuiReader.choice(1, 3);
        switch (choice) {
            case 1 :
                System.out.println("ID:");
                String userId = TuiReader.readInputCorrect();
                System.out.println("PassWord:");
                String password = TuiReader.readInputCorrect();
                customerID = customerManagementTeam.login(userId,password);
                System.out.println("로그인 성공");
                customerNotice();
                customerMenu();
                break;
            case 2 :
                System.out.println("ID:");
                userId = TuiReader.readInputCorrect();
                System.out.println("PassWord:");
                password = TuiReader.readInputCorrect();
                System.out.println("Name:");
                String customerName = TuiReader.readInputCorrect();
                customerManagementTeam.join(userId,password,customerName);
                System.out.println("회원가입 성공");
                break;
            case 3 :
                customerID=-1;
                employeeMenu();
                break;
            default:
                throw new CIllegalArgumentException("잘못된 입력입니다.");
        }
    }
    private static void customerMenu() {
        boolean isContinue = true;
        while (isContinue) {
            try {
                System.out.println("********************* MENU *********************");
                System.out.println(" 1. 상담 접수");
                System.out.println(" 2. 보험 가입");
                System.out.println(" 3. 보험금 신청");
                System.out.println(" 0. 종료");
                int choice = TuiReader.choice(0, 3);
                switch (choice) {
                    case 1:
                        counselingApply();
                        break;
                    case 2:
                        registerInsurance();
                        break;
                    case 3:
                        applyReward();
                        break;
                    case 0:
                        isContinue = false;
                        break;
                    default:
                        throw new CIllegalArgumentException("잘못된 입력입니다.");
                }
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void customerNotice() {
    	// 최초 로그인 시 자신의 계정에 와있는 알림을 확인한다.
    	if( customerID != -1 ) {
            List<AdviceNote> adviceList = contractManagementTeam.getAllAdviceNote();
            Vector<AdviceNote> myAdviceNote = new Vector<>();
            for( AdviceNote advice : adviceList ) {
                if( advice.getCustomerID()==customerID ) myAdviceNote.add( advice );
            }
            if( myAdviceNote.size()!=0 ) {
                System.out.println("안내문이 있습니다.");
                for( AdviceNote advice : adviceList ) {
                    Contract expireContract = contractManagementTeam.getContract( advice.getContractID() );
                    Insurance expireInsurance = contractManagementTeam.getInsurance( expireContract.getInsuranceID() );
                    System.out.println( expireInsurance.getInsuranceName() + " 상품에 대한 계약이 만기되었습니다." );
                    System.out.println("1. 재계약   2. 확인 ");
                    int choice = TuiReader.choice( 1,  2 );
                    if( choice==1 ) {
                        advice.setResult( Result.ACCEPT );
                        contractManagementTeam.setAdviceNote( advice );
                        contractManagementTeam.manage( Target.ADVICE_NOTE, Crud.UPDATE );
                        System.out.println("재계약 신청이 완료되었습니다.");
                    } else if( choice==2 ) {
                        advice.setResult( Result.DENY );
                        contractManagementTeam.setAdviceNote( advice );
                        contractManagementTeam.manage( Target.ADVICE_NOTE, Crud.UPDATE );
                    }
                }
            }
    	}

    }
    private static void printEmployeeMenu() {
        System.out.println("********************* MENU *********************");
        System.out.println(" 1. 상품개발");
        System.out.println(" 2. 인수정책");
        System.out.println(" 3. 인수심사");
        System.out.println(" 4. 캠페인 프로그램");
        System.out.println(" 5. 영업 활동");
        System.out.println(" 6. 보상 처리");
        System.out.println(" 7. 분납/수금 관리");
        System.out.println(" 8. 교육 수료자 관리");
        System.out.println(" 9. 교육 추가");
        System.out.println(" 10. 만기 계약 관리");
        System.out.println(" 11. 계약 관리 지침");
        System.out.println(" 12. 계약 통계 관리");
        System.out.println(" 13. 영업 조직 관리");
        System.out.println(" 0. 종료 ");
    }
    private static void employeeMenu() {
        boolean isContinue = true;
        while (isContinue) {
            try {
                printEmployeeMenu();
                int choice = TuiReader.choice(0, 12);
                switch (choice) {
                    case 1:
                        createInsurance();
                        break;
                    case 2:
                        // 인수 정책 관련 CRUD
                        uwPolicy();
                        break;
                    case 3:
                        // 인수 심사 -> Contract 불러오기
                        underWriting();
                        break;
                    case 4:
                        // 캠페인 프로그램 관련
                        campaignProgramMenu();
                    case 5:
                        processSales();
                        break;
                    case 6:
                        processReward();
                        break;
                    case 7:
                    	managePayment();
                    	break;
                    case 8:
                    	manageStudent();
                    	break;
                    case 9:
                    	manageEducation();
                    	break;
                    case 10:
                    	manageExpireContract();
                    	break;
                    case 11:
                    	addContractManagementPolicy();
                    	break;
                    case 12:
                    	manageContractAnalysis();
                    	break;
                    case 13:
                        businessTeamManage();
                        break;
                    case 0:
                        isContinue = false;
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
    private static void createInsurance() {
        System.out.println("********************* 상품 개발 *********************");
        System.out.println(" 1. 상품 기획 버튼");
        System.out.println(" 2. 상품 개발 버튼");
        System.out.println(" 3. 상품 인가 버튼");
        int choice = TuiReader.choice(1, 3);
        switch (choice) {
            case 1:
                createInsurancePlan();
                break;
            case 2:
                Insurance insurance = designInsurance();
                estimateProfit(insurance);
                insurance.setInsuranceState(InsuranceState.DESIGNED);
                insuranceList.update(insurance);
                System.out.println("저장 완료되었습니다. 요율 분석 버튼을 눌러 자동으로 이동합니다.");
                analyzeInsuranceRate(insurance);
                insuranceList.update(insurance);
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
    private static void processSales() {
        System.out.println("********************* 영업 활동 *********************");
        System.out.println(" 1. 영업 활동 계획");
        System.out.println(" 2. 상담 일정 수립");
        System.out.println(" 3. 대면 상담");
        int choice = TuiReader.choice(1, 3);
        switch (choice) {
            case 1:
                salesPlan();
                break;
            case 2:
                setConsultationSchedule();
                break;
            case 3:
                faceToFaceConsultation();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
    private static void createInsurancePlan() {
        System.out.println("********************* 상품 기획 *********************");
        System.out.println(" 1. 새상품 기획");
        System.out.println(" 2. 기존 상품 관리");
        int choice = TuiReader.choice(1, 2);
        switch (choice) {
            case 1:
                System.out.println("고객니즈 및 경쟁사의 동향 분석 보고서를 작성하세요: ");
                insuranceDevelopmentTeam.plan(Target.INSURANCE, Crud.CREATE);
                System.out.println("새 상품 기획안을 저장하였습니다.");
                break;
            case 2:
                System.out.println("********************* 보험 기획안 *********************");
                List<Insurance> insurances = insuranceList.retrieveAll()
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
                int choice2 = TuiReader.choice(0, insurances.size()-1);
                System.out.println("수정: 1, 삭제: 2");
                int choice3 = TuiReader.choice(1, 2);
                Insurance findInsurance = insurances.get(choice2);
                if (choice3 == 1) {
                    System.out.println("기존 기획안: " + insurances.get(choice2).getPlanReport());
                    System.out.println("수정할 기획안을 입력해주세요.");
                    String report = TuiReader.readInput("보고서가 올바른 형식이 아닙니다.");
                    findInsurance.setPlanReport(report);
                    insuranceList.update(findInsurance);
                    System.out.println("상품 기획안을 수정하였습니다.");
                } else if (choice3 == 2) {
                    insuranceList.delete(findInsurance.getInsuranceID());
                    System.out.println("해당 상품 기획안을 삭제하였습니다.");
                }
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
    private static Insurance designInsurance() {
        List<Insurance> insurances = insuranceList.retrieveAll()
            .stream()
            .filter(insurance -> insurance.getInsuranceState() == InsuranceState.PLANED)
            .collect(Collectors.toList());
        if (insurances.size() == 0) {
            throw new CInsuranceNotFoundException("설계할 보험 기획안이 없습니다.");
        }
        System.out.println("********************* 상품 개발 *********************");
        System.out.println("설계할 보험 기획안의 번호를 입력해주세요.");
        for (int i = 0; i < insurances.size(); i++) {
            System.out.println(i + ". " + insurances.get(i).getPlanReport());
        }
        int choiceId = TuiReader.choice(0, insurances.size()-1);
        System.out.println("설계할 보험 기획안: " + insurances.get(choiceId).getPlanReport());
        System.out.println(
            "설계할 보험의 이름, 상품 종류, 판매 대상, 가입 조건, 보험료, 보장 내용, 개발 예상 비용을 / 로 구분하여 입력해주세요.");
        boolean isSuccessInput = false;
        Insurance insurance = insurances.get(choiceId);
        while (!isSuccessInput) {
            try {
                String[] input = TuiReader.readInput("다시 입력하세요.").split("/");
                if (input.length != 7) {
                    throw new CIllegalArgumentException("다시 입력하세요");
                }
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
        int choice = TuiReader.choice(1, 2);
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
        List<Insurance> insurances = insuranceList.retrieveAll()
            .stream().filter(insurance -> insurance.getInsuranceState() == InsuranceState.DESIGNED)
            .collect(Collectors.toList());
        if (insurances.size() == 0) {
            throw new CInsuranceNotFoundException("인가할 보험이 없습니다.");
        }
        for (int i = 0; i < insurances.size(); i++) {
            System.out.println(i + ". " + insurances.get(i).getInsuranceName());
        }
        int choice = TuiReader.choice(0, insurances.size()-1);
        Insurance insurance = insurances.get(choice);
        LocalDateTime authorizedDate = OuterActor.authorizedInsurance(insurance);
        if (insurance.getInsuranceState() == InsuranceState.AUTHORIZED) {
            insuranceList.update(insurance);
            System.out.println(
                authorizedDate.getMonth().getValue() + "월 " + authorizedDate.getDayOfMonth() + "일에 합격 되었습니다");
        } else {
            System.out.println("불합격 되었습니다.");
        }
    }
    private static void registerInsurance() {
        // 보험을 가입하다 usecase 부분
        System.out.println("********************* 보험 가입 *********************");
        List<Insurance> registList = insuranceList.retrieveAll()
                .stream()
                .filter(insurance -> insurance.getInsuranceState() == InsuranceState.AUTHORIZED)
                .toList();
        // 고객이 가입할 보험 리스트 출력
        for (int i = 0; i < registList.size(); i++) {
            System.out.println(i + ". " + registList.get(i).getInsuranceName() + " " + registList.get(i).getGuarantee()
                + " " + registList.get(i).getPayment()
                + " " + registList.get(i).getDuration());
        }
        // 고객이 가입할 보험 클릭
        int registChoice = TuiReader.choice(0, registList.size() - 1);
        // 보험 가입 유스케이스 6번 시나리오 - 사용자의 동의 부분 X
        System.out.println("보험 이름: " + registList.get(registChoice).getInsuranceName()
                + ", 기간: " + registList.get(registChoice).getDuration()
                + ", 월 납입료: " + registList.get(registChoice).getPayment());
        System.out.println("1. 신청");
        System.out.println("2. 취소");
        int contractChoice = TuiReader.choice(1, 2);
        switch(contractChoice) {
            case 1:
                Contract contract = new Contract();
                // 인수 심사 전 basic, collaborative 구분
                // TODO retrieve 고치고 다시 확인
                Customer uwCustomer = customerList.retrieve(customerID);
                if(uwCustomer.getIncomeLevel() > 5) {
                    // 가입 신청 시 소득 분위에 따라 state 지정 - 인수 심사 경우
                    contract.setContractUWState(ContractUWState.Basic);
                    // 로그아웃 시 고객 ID null값 처리
                    contract.setCustomerID(customerID);
                    contract.setInsuranceID(registList.get(registChoice).getInsuranceID());
                    contract.setContractDate(LocalDate.now());
                    contract.setContractState(ContractState.Online);
                    contract.setContractRunState(ContractRunState.Ready);
                    contractList.add(contract);
                    System.out.println("보험 가입 신청이 완료되었습니다.");
                } else if(uwCustomer.getIncomeLevel() <= 5) {
                    // 가입 신청 시 소득 분위에 따라 state 지정 - 공동 인수 심사 경우
                    contract.setContractUWState(ContractUWState.Collaborative);
                    // 로그인 하면 해당 고객의 ID를 전역에 배치 - 로그아웃 시 고객 ID null값 처리
                    contract.setCustomerID(customerID);
                    contract.setInsuranceID(registList.get(registChoice).getInsuranceID());
                    contract.setContractDate(LocalDate.now());
                    contract.setContractState(ContractState.Online);
                    contract.setContractRunState(ContractRunState.Ready);
                    contractList.add(contract);
                    System.out.println("보험 가입 신청이 완료되었습니다.");
                }
                break;
            case 2:
                break;
        }
    }
    private static void uwPolicy() {
        System.out.println("********************* 인수 정책 *********************");
        System.out.println(" 1. 인수 정책 열람");
        System.out.println(" 2. 인수 정책 수립");
        int uwchoice = TuiReader.choice(1, 2);
        switch (uwchoice) {
            case 1:
                retrieveUWPolicy();
                break;
            case 2:
                createUWPolicy();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
    private static void createUWPolicy() {
        System.out.println("********************* 인수 정책 수립 *********************");
        System.out.println("인수 정책 제목 / 인수 정책 내용 / 인수 정책 기준 : ");
        underwritingTeam.establishPolicy(Constants.Target.ASSUME_POLICY, Constants.Crud.CREATE);
        System.out.println("인수 정책이 저장되었습니다.");
    }
    private static void retrieveUWPolicy() {
        System.out.println("********************* 인수 정책 열람 *********************");
        List<AssumePolicy> policyList = assumePolicyList.getAllPolicy();
        if(policyList != null) {
            for (int i = 0; i < policyList.size(); i++) {
                System.out.println(i + ". " + policyList.get(i).getPolicyID() + " " + policyList.get(i).getName());
                System.out.println("인수정책 내용: " + policyList.get(i).getContent());
            }
        } else {
            System.out.println("현재 인수 정책이 존재하지 않습니다.");
        }
    }

    /*private static void deleteUWPolicy() {
        System.out.println("********************* 인수 정책 열람 *********************");
        System.out.println("삭제할 인수 정책의 번호를 입력해주세요.");
        underwritingTeam.establishPolicy(Constants.Target.ASSUME_POLICY, Constants.Crud.DELETE);
        System.out.println("해당 인수 정책을 삭제하였습니다.");
    }*/

    private static void underWriting() {
        // 인수 심사하다 usecase 부분
        System.out.println("********************* 인수 심사 *********************");
        System.out.println(" 1. 인수 심사 접수");
        System.out.println(" 2. 공동 인수 접수");
        int choiceUWC = TuiReader.choice(1, 2);
        switch (choiceUWC) {
            case 1:
                basicUW();
                break;
            case 2:
                collaborateUW();
                break;
        }
    }
    private static void basicUW() {
        // 인수 심사 대상 리스트 출력
        List<Contract> registList = contractList.retrieveAll()
                .stream()
                .filter(contract -> contract.getContractState() == ContractState.Online
                        && contract.getContractRunState() == ContractRunState.Ready
                        && contract.getContractUWState() == ContractUWState.Basic)
                .toList();

        if(registList != null) {
            for (int i = 0; i < registList.size(); i++) {
                System.out.println(i + ". " + registList.get(i).getContractID());
            }
        } else {
            System.out.println("현재 인수 심사 대상이 존재하지 않습니다.");
        }
        int inChoice = TuiReader.choice(0, registList.size() - 1);
        if(registList.get(inChoice).getContractID() != 0) {
            // 인수 심사 시작
            registList.get(inChoice).setContractRunState(ContractRunState.Finish);
                System.out.println("해당 고객의 보험 가입 신청을 처리했습니다.");
            }
        }

    private static void collaborateUW() {
        // 공동 인수 시작
        // 인수 심사 대상 리스트 출력
        List<Contract> registCList = contractList.retrieveAll()
                .stream()
                .filter(contract -> contract.getContractState() == ContractState.Online
                        && contract.getContractRunState() == ContractRunState.Ready
                        && contract.getContractUWState() == ContractUWState.Collaborative)
                .toList();

        if(registCList != null) {
            for (int i = 0; i < registCList.size(); i++) {
                System.out.println(i + ". " + registCList.get(i).getContractID());
            }
        } else {
            System.out.println("현재 인수 심사 대상이 존재하지 않습니다.");
        }
        int inChoice = TuiReader.choice(0, registCList.size() - 1);
        if(registCList.get(inChoice).getContractID() != 0) {
            // 외부 Actor에 계약 명단 전달
            OuterActor.collaborateUW(registCList.get(inChoice));
            System.out.println("해당 고객의 보험 가입 신청을 처리했습니다.");
        }
    }
    private static void campaignProgramMenu() {
        System.out.println(" 1. 새로운 캠페인 프로그램 기획");
        System.out.println(" 2. 캠페인 프로그램 열람");
        int campaignChoice = TuiReader.choice(1, 2);
        switch(campaignChoice) {
            case 1:
                // 캠페인 프로그램 기획
                campaignPlan();
                break;
            case 2:
                // 캠페인 프로그램 열람
                retrieveCampaignProgram();
                break;
        }
    }
    private static void campaignPlan() {
        // 기획안이 통과되지 않은 경우 구현 X
        System.out.println("********************* 새로운 캠페인 프로그램 기획 *********************");
        System.out.println("인가 확정 상품 / 마케팅 대상 / 마케팅 기간 / 캠페인 수단 / 마케팅 예산 / 예상 손익률 : ");
        marketingPlanningTeam.plan(Target.CAMPAIGN_PROGRAM, Crud.CREATE);
        System.out.println("새로운 캠페인 프로그램 기획이 저장되었습니다!");
        //printMenu();
    }

    private static void retrieveCampaignProgram() {
        System.out.println("********************* 캠페인 프로그램 열람 *********************");
        System.out.println(" 1. 현재 진행 중인 캠페인");
        System.out.println(" 2. 지난 캠페인");
        System.out.println(" 3. 새로운 캠페인 실행");
        System.out.println(" 4. 캠페인 기획안 수정");
        int rtCampaignChoice = TuiReader.choice(1, 4);
        switch (rtCampaignChoice) {
            case 1:
                runningCampaign();
                break;
            case 2:
                endCampaign();
                break;
            case 3:
                runCampaign();
                break;
            case 4:
                modifyCampaignProgramPlan();
                break;
        }
    }

    private static void runningCampaign() {
        // 실행 중인 캠페인 리스트 조회 - 시나리오 X
        System.out.println("********************* 실행 중인 캠페인 페이지 *********************");
        List<CampaignProgram> campaignPrograms = campaignProgramList.retrieveAll()
                .stream()
                .filter(campaignProgram -> campaignProgram.getProgramState() == CampaignState.Run)
                .collect(Collectors.toList());
        for(CampaignProgram campaignProgram : campaignPrograms) {
            System.out.println("캠페인 이름: " + campaignProgram.getCampaignName()
                    + "/ 대상 보험: " + campaignProgram.getInsuranceID() + "/ 캠페인 대상: " + campaignProgram.getCampaignTarget()
                    + "/ 기간: " + campaignProgram.getDuration() + "/ 장소: " + campaignProgram.getPlace()
                    + "/ 예산: " + campaignProgram.getBudget() + "/ 예상 손익률: " + campaignProgram.getExResult());
        }
    }

    private static void endCampaign() {
        // 지난 캠페인 리스트 조회 - 시나리오 X but 캠페인 프로그램 결과 분석 시나리오 O
        // 지난 캠페인 리스트는 DB에 미리 구현해놓기
        System.out.println("********************* 지난 캠페인 페이지 *********************");
        // 지난 캠페인 리스트 필터링
        List<CampaignProgram> campaignPrograms = campaignProgramList.retrieveAll()
                .stream()
                .filter(campaignProgram -> campaignProgram.getProgramState() == CampaignState.End)
                .collect(Collectors.toList());
        // 지난 캠페인 리스트 출력
        for(CampaignProgram campaignProgram : campaignPrograms) {
            System.out.println("캠페인 이름: " + campaignProgram.getCampaignName()
                    + "/ 대상 보험: " + campaignProgram.getInsuranceID() + "/ 캠페인 대상: " + campaignProgram.getCampaignTarget()
                    + "/ 기간: " + campaignProgram.getDuration() + "/ 장소: " + campaignProgram.getPlace()
                    + "/ 예산: " + campaignProgram.getBudget() + "/ 예상 손익률: " + campaignProgram.getExResult()
            );
        // 지난 캠페인 리스트 선택
        int endCampaignChoice = TuiReader.choice(1, campaignPrograms.size()-1);
        // 선택한 캠페인에 실제 손익률(임의의 값 - 5.5f로 지정) 추가
        float endResult = campaignProgram.getEndResult();
        campaignPrograms.get(endCampaignChoice).setEndResult(endResult);
        // 캠페인 프로그램 보고서 저장
        campaignProgram.setReport(campaignPrograms.get(endCampaignChoice));
        }
    }
    private static void runCampaign() {
        // 캠페인 프로그램 실행 유스케이스 시나리오의 8번 제외 - 외부 Actor 배제
        System.out.println("********************* 캠페인 프로그램 실행 *********************");
        // 통과된 기획 리스트 보여주기 - DB
        List<CampaignProgram> campaignPrograms = campaignProgramList.retrieveAll()
                .stream().filter(campaignProgram -> campaignProgram.getProgramState() == CampaignState.Plan)
                .toList();
        for(CampaignProgram campaignProgram : campaignPrograms) {
            System.out.println(campaignProgram.getInsuranceID()
                    + " " + campaignProgram.getDuration() + " " + campaignProgram.getCampaignTarget()
                    + " " + campaignProgram.getPlace() + " " + campaignProgram.getOutTeam());
        }
        // 실행할 campaignProgram 선택
        System.out.println("실행할 캠페인 프로그램을 선택하세요.");
        int campaignChoice = TuiReader.choice(0, campaignPrograms.size() - 1);

        CampaignProgram campaignProgram = campaignPrograms.get(campaignChoice);
        // 외부 엑터에 전달
        OuterActor.runProgram(campaignProgram);
        System.out.println("해당 프로그램이 실행됩니다.");
//        printMenu();
    }
    private static void applyReward() {
    	// 고객 ID를 어떻게 가져오는가...
    	int customerID = 0;
    	teams.RewardTeam rewardTeam = new teams.RewardTeam();
    	// 이 고객 ID로 가입된 계약 목록
    	Vector<Contract> assignedContract = rewardTeam.getCustomerContract( customerID );
    	Vector<Insurance> assignedInsurances = rewardTeam.getCustomerInsurance( customerID );
    	System.out.println("현재 가입된 보험 목록입니다. 보험금 신청을 원하는 보험의 번호를 입력하세요");
    	System.out.println("---------------------------");
    	if( assignedInsurances.size() == 0 ) {
    		System.out.println( "가입된 보험이 없습니다" );
    		return;
    	}
    	for( int i=0; i<assignedInsurances.size(); i++ ) {
    		String tmpInsuranceName = assignedInsurances.get(i).getInsuranceName();
    		System.out.println(i + ": " + tmpInsuranceName );
    	}
    	System.out.println("---------------------------");
    	int selectedContract = TuiReader.choice(0, assignedInsurances.size()-1);
    	int selectedInsuranceID = assignedInsurances.get(selectedContract).getInsuranceID();
    	System.out.println("신청을 위해서 다음 정보가 필요합니다.");
    	// 신청 조건이 기입되어 있는 곳이 없음...
    	//System.out.println("신청 조건: " + assignedInsurances.get(choice).get);
    	System.out.println("사고 증빙 서류: 사고 증명서, 사진 등");
    	System.out.println("본인 증빙 서류: 주민등록증 사본, 가족관계증명서 등");
    	System.out.println("위 내용을 확인하셨다면 '확인'버튼을 누르세요.");
    	System.out.println("1. 확인");
    	int choice = TuiReader.choice(1, 1);
    	System.out.println("신청 내용을 입력하고 증빙 서류를 첨부해주세요.");
    	String userInput = TuiReader.readInput(null);
    	String[] inputList = userInput.split("/");
    	System.out.println( "1. 신청     2. 취소");
    	choice = TuiReader.choice(1, 2);
    	if( choice==2 ) return;
    	System.out.println( "신청이 완료되었습니다" );
    	// 위 정보를 토대로 Reward 엔티티 생성
    	Reward applyReward = new Reward();
    	applyReward.setContent( inputList[0] );
    	applyReward.setAccidentProfile( inputList[1] );
    	applyReward.setIdentifyProfile( inputList[2] );
    	applyReward.setAppliResult( Result.PROCESS );
    	applyReward.setAppliDate( LocalDate.now() );
    	applyReward.setReward(0);
    	applyReward.setContractID( assignedContract.get( selectedContract ).getContractID() );
    	Customer tmpCustomer = rewardTeam.getCustomerInformation( customerID );
    	applyReward.setCustomerName( tmpCustomer.getName() );
    	rewardTeam.setReward( applyReward );
    	rewardTeam.manage( Target.REWARD, Crud.UPDATE );
    }
    private static void modifyCampaignProgramPlan() {
        System.out.println("********************* 캠페인 프로그램 기획안 수정 *********************");
        // 캠페인 프로그램 진행 전, 중 상태의 기획안만 수정 - 시나리오 X
    }
    private static void processReward() {
        teams.RewardTeam rewardTeam = new teams.RewardTeam();
        Vector<Reward> rewardList = rewardTeam.getAllReward();
        // Alternate 1
        if( rewardList.size() == 0 ) { System.out.println( "접수된 보상 요청이 없습니다" );}
        else {
            System.out.println( "------------------------------" );
            for( int i=0; i < rewardList.size(); i++ ) {
                System.out.println( rewardList.get(i).getCustomerName() + ": " +
                    rewardList.get(i).getAppliResult().getString() +
                    " " + rewardList.get(i).getContent() +
                    " " + Integer.toString( rewardList.get(i).getAppliDate().getMonthValue() ) + "월 " +
                    Integer.toString( rewardList.get(i).getAppliDate().getDayOfMonth() ) + "일 " );
            }
            System.out.println( "------------------------------" );
            int select = TuiReader.choice(0, rewardList.size()-1);
            Reward selectedReward = rewardList.get( select );
            System.out.println( selectedReward.getCustomerName() + " - " + selectedReward.getContent() );
            System.out.println( "해당 보험에 대해 승인하시겠습니까?" );
            System.out.println( "1. 보상 지급   2. 요청 거절" );
            int resultSelect = TuiReader.choice(1, 2);
            if( resultSelect == 1 ) {
                // 승인
                System.out.println( selectedReward.getCustomerName() );
                // "/"로 구분해서 한 줄로 받기로 컨벤션을 정했으나... 음...
                System.out.println( "지급 금액: " + Integer.toString( selectedReward.getReward() ) + "원" );
                System.out.println( "책임자 이름: 사용자" );
                System.out.println( "내용: " + selectedReward.getContent() );
                System.out.println( "--------- 해당 요청에 대한 보상을 지급합니다. -----------");
                rewardTeam.rewardResult( selectedReward.getRewardID(), Constants.Result.ACCEPT );
            } else if( resultSelect == 2 ) {
                // Alternate 2
                System.out.print( "보상 거절 사유를 입력하세요: ");
                String reason = TuiReader.readInput( "정확한 사유를 입력하세요" );
                selectedReward.setContent( reason );   		// DENY된 보험 신청의 경우, Content에 거절 사유를 남긴다.
                rewardTeam.rewardResult( selectedReward.getRewardID(), Constants.Result.DENY );
            } else {
                //
            }
        }
    }
    private static void salesPlan() {
        System.out.println("인가 합격된 보험 상품 리스트");
        List<Insurance> insurances = insuranceList.retrieveAll()
            .stream().filter(insurance -> insurance.getInsuranceState() == InsuranceState.AUTHORIZED)
            .collect(Collectors.toList());
        if (insurances.size() == 0) {
            throw new CInsuranceNotFoundException("인가된 보험이 없습니다.");
        }
        for (int i = 0; i < insurances.size(); i++) {
            System.out.println(i + ". " + insurances.get(i).getInsuranceName());
        }
        int choice1 = TuiReader.choice(0, insurances.size()-1);
        Insurance insurance = insurances.get(choice1);
        CampaignProgram campaignProgram = campaignProgramList.retrieve(insurance.getInsuranceID());
        // TODO: db 추가되면 null 말고 다른 값으로 넘어오는지 확인해야 함
        if (campaignProgram == null) {
            throw new CInsuranceNotFoundException("해당 보험 상품이 진행한 캠페인이 없습니다.");
        }
        System.out.println("캠페인 이름: " + campaignProgram.getCampaignName());
        int choice2;
        boolean isSuccessInput = false;
        do {
            List<UserPersona> userPersonas = userPersonaList.retrieveAll()
                .stream()
                .filter(userPersona -> userPersona.getInsuranceId() == insurance.getInsuranceID())
                .collect(Collectors.toList());
            System.out.println("-----유저 퍼소나 리스트-----");
            for (UserPersona userPersona : userPersonas) {
                System.out.print("성별: " + userPersona.getSex() + " / ");
                System.out.print("나이: " + userPersona.getAge() + " / ");
                System.out.print("직업: " + userPersona.getJob() + " / ");
                System.out.println("소득수준: " + userPersona.getIncomeLevel());
            }
            System.out.println("--------------------------");
            System.out.println("1. 유저 퍼소나 추가");
            System.out.println("2. 영업 인력 계획 수립");
            choice2 = TuiReader.choice(1, 2);
            while (choice2 == 1 && !isSuccessInput) {
                try {
                    System.out.println("유저 퍼소나 성별, 나이, 직업, 소득수준을 /로 구분하여 입력하세요.");
                    String[] userPersonaInfo = TuiReader.readInput("유저 퍼소나 입력란에 정확히 입력하세요.")
                        .split("/");
                    if (userPersonaInfo.length != 4) {
                        throw new CIllegalArgumentException("입력 형식이 잘못되었습니다.");
                    }
                    UserPersona userPersona = new UserPersona();
                    userPersona.setInsuranceId(insurance.getInsuranceID());
                    userPersona.setSex(Gender.valueOf(userPersonaInfo[0]));
                    userPersona.setAge(Integer.parseInt(userPersonaInfo[1]));
                    userPersona.setJob(userPersonaInfo[2]);
                    userPersona.setIncomeLevel(Integer.parseInt(userPersonaInfo[3]));
                    userPersonaList.add(userPersona);
                    System.out.println("유저 퍼소나가 저장되었습니다.");
                    isSuccessInput = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            isSuccessInput = false;
        } while (choice2 == 1);
        isSuccessInput = false;
        while (!isSuccessInput) {
            try {
                // TODO: 영업 활동 시작일이 종료일보다 빨라야 한다.
                System.out.println("영업 활동 시작일, 종료일, 예상 목표 인원, 판매 방식을 /로 구분하여 입력하세요.");
                String[] salesPlanInfo = TuiReader.readInput("영업 활동 계획란에 정확히 입력하세요.")
                    .split("/");
                if (salesPlanInfo.length != 4) {
                    throw new CIllegalArgumentException("입력 형식이 잘못되었습니다.");
                }
                insurance.setSalesStartDate(LocalDate.parse(salesPlanInfo[0]));
                insurance.setSalesEndDate(LocalDate.parse(salesPlanInfo[1]));
                insurance.setGoalPeopleNumber(Integer.parseInt(salesPlanInfo[2]));
                insurance.setSalesMethod(salesPlanInfo[3]);
                insuranceList.update(insurance);
                System.out.println("영업 활동 계획을 수립하였습니다.");
                isSuccessInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void setConsultationSchedule() {
        System.out.println("*************** 상담 일정 ***************");
        List<CustomerCounseling> counselingList = customerCounselingList.retrieveAll()
            .stream()
            .filter(counseling -> counseling.getCounselingState() == CounselingState.APPLIED)
            .collect(Collectors.toList());
        if (counselingList.size() == 0) {
            throw new CCounselingNotFoundException("대면 상담 요청된 고객이 없습니다.");
        }
        List<Integer> customerIdList = counselingList.stream()
            .map(CustomerCounseling::getCustomerId)
            .distinct()
            .collect(Collectors.toList());
        for (int i = 0; i < customerIdList.size(); i++) {
            Customer customer = customerList.retrieve(customerIdList.get(i));
            System.out.println(i + ". " + customer.getName());
        }
        int choiceCustomer = TuiReader.choice(0, customerIdList.size() - 1);
        Customer customer = customerList.retrieve(customerIdList.get(choiceCustomer));
        List<CustomerCounseling> choiceCustomerCounselingList = counselingList.stream()
            .filter(counseling -> counseling.getCustomerId() == customer.getCustomerID())
            .collect(Collectors.toList());
        for (int i = 0; i < choiceCustomerCounselingList.size(); i++) {
            CustomerCounseling counseling = choiceCustomerCounselingList.get(i);
            System.out.println(i + ". 상담 희망 장소: " + counseling.getCounselingPlace()
                + " 상담 희망일: "+ counseling.getCounselingTime().getMonthValue() + "월 "
                + counseling.getCounselingTime().getDayOfMonth() + "일 "
                + "상담 시간: " + counseling.getCounselingTime().getHour() + "시 "
                + counseling.getCounselingTime().getMinute() + "분");
        }
        int choiceCounseling = TuiReader.choice(0, choiceCustomerCounselingList.size() - 1);
        CustomerCounseling counseling = choiceCustomerCounselingList.get(choiceCounseling);
        counseling.setCounselingState(CounselingState.ACCEPTED_APPLY);
        customerCounselingList.update(counseling);
        OuterActor.sendSMS("상담 일정이 잡혔습니다.");
        System.out.println("상담 일정이 잡혔습니다.");
    }
    private static void faceToFaceConsultation() {
        System.out.println("*************** 대면 상담 ***************");
        List<CustomerCounseling> counselingList = customerCounselingList.retrieveAll()
            .stream()
            .filter(counseling -> counseling.getCounselingState() == CounselingState.ACCEPTED_APPLY)
            .collect(Collectors.toList());
        if (counselingList.size() == 0) {
            throw new CCounselingNotFoundException("대면 상담 수락된 고객이 없습니다.");
        }
        System.out.println(" 고객 리스트 ");
        for (int i = 0; i < counselingList.size(); i++) {
            CustomerCounseling counseling = counselingList.get(i);
            Customer customer = customerList.retrieve(counseling.getCustomerId());
            System.out.println(i + ". " + customer.getName());
        }
        int choiceCustomer = TuiReader.choice(0, counselingList.size() - 1);
        // TODO: 선택한 고객이 상담 시간이 아닌 경우 수정
        CustomerCounseling counseling = counselingList.get(choiceCustomer);
        if (counseling.getCounselingTime().isAfter(LocalDateTime.now())) {
            throw new CCounselingNotFoundException("상담 시간이 아닙니다.");
        }
        Customer customer = customerList.retrieve(counseling.getCustomerId());
        boolean isSuccessInput = false;
        while (!isSuccessInput) {
            try {
                System.out.println("고객 이름, 나이, 성별, 연락처, 소득 수준, 계좌번호, 계좌 비밀번호를 /로 구분하여 입력하세요.");
                String[] input = TuiReader.readInput("고객 정보를 정확히 입력해주세요").split("/");
                if (input.length != 7) {
                    throw new CIllegalArgumentException("입력 형식이 잘못되었습니다.");
                }
                customer.setName(input[0]);
                customer.setAge(Integer.parseInt(input[1]));
                customer.setSex(Gender.valueOf(input[2]));
                customer.setPhoneNumber(input[3]);
                customer.setIncomeLevel(Integer.parseInt(input[4]));
                customer.setAccountNumber(input[5]);
                customer.setAccountPassword(input[6]);
                isSuccessInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        customerList.update(customer);
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("추천 보험 리스트");
            List<Insurance> recommendInsurances = sellGroupTeam.recommendInsurance(customer);
            for (int i = 0; i < recommendInsurances.size(); i++) {
                Insurance insurance = recommendInsurances.get(i);
                System.out.println(i + ". " + insurance.getInsuranceName());
            }
            int choiceInsurance = TuiReader.choice(0, recommendInsurances.size() - 1);
            Insurance insurance = recommendInsurances.get(choiceInsurance);
            System.out.println("추천 보험 상품: " + insurance.getInsuranceName());
            System.out.println(" 보험료 가계산: " + sellGroupTeam.calculateInsuranceFee(insurance, customer));
            System.out.println(" 보험 추천 이유: " + sellGroupTeam.recommendInsuranceReason(insurance, customer));
            System.out.println("보험 선택: 1, 보험 판매 실패: 2, 뒤로 가기: 3");
            int choice = TuiReader.choice(1, 3);
            if (choice == 1) {
                contract(insurance, customer);
                isContinue = false;
            } else if (choice == 2) {
                System.out.println("보험 판매에 실패했습니다.");
                isContinue = false;
            }
        }
    }
    private static void contract(Insurance insurance, Customer customer) {
        System.out.println("*************** 청약서 작성 ***************");
        String contractFile = TuiReader.readInput(" 저장에 실패했습니다.");
        Contract contract = new Contract();
        contract.setInsuranceID(insurance.getInsuranceID());
        contract.setCustomerID(customer.getCustomerID());
        contract.setContractFile(contractFile);
        contract.setContractDate(LocalDate.now());
        contract.setContractState(ContractState.Offline);
        contract.setContractRunState(ContractRunState.Ready);
        ContractUWState contractUWState = customer.getIncomeLevel() < 5 ?
            ContractUWState.Collaborative : ContractUWState.Basic;
        contract.setContractUWState(contractUWState);
        contractList.add(contract);
        System.out.println("청약서 저장이 완료됐습니다. 인수 심사로 넘어갑니다.");
        underWriting();

    }
    private static void counselingApply() {
        System.out.println("*************** 상담 신청 ***************");
        boolean isSuccessInput = false;
        while (!isSuccessInput) {
            try {
                System.out.println("상담 희망 장소, 상담 희망일(20**-**-**), 상담 희망 시간(**:**:**)을 /로 구분하여 입력하세요.");
                String[] counselingInfo = TuiReader.readInput("상담 신청란에 정확히 입력하세요.").split("/");
                if (counselingInfo.length != 3) {
                    throw new CIllegalArgumentException("입력 형식이 잘못되었습니다.");
                }
                CustomerCounseling counseling = new CustomerCounseling();
                counseling.setCustomerId(customerID);
                counseling.setCounselingPlace(counselingInfo[0]);
                counseling.setCounselingTime(LocalDateTime.parse(counselingInfo[1] + "T" + counselingInfo[2]));
                counseling.setCounselingState(CounselingState.APPLIED);
                customerCounselingList.add(counseling);
                isSuccessInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("접수 완료되었습니다.");
    }
    private static void manageStudent() {
    	Vector<Education> educationList = businessEducationTeam.getAllEducation();
    	if( educationList.size()==0 )System.out.println("아직 수행된 교육이 없습니다.");
    	else {
	    	System.out.println("--------------------교육 수료자를 관리할 교육을 선택하세요 -----------------------");
	    	for( int i=0; i < educationList.size(); i++ ) {
	    		System.out.println( i + ": " + educationList.get(i).getName() + "( " + educationList.get(i).getTeacherName()  + ") " );
	    	}
	    	System.out.println("----------------------------------------------------------------------");
	    	int select = TuiReader.choice(0, educationList.size() - 1);
	    	int selectedEducationID = educationList.get(select).getEducationID();

	    	System.out.println("수행할 작업을 선택하세요");
	    	System.out.println("1. 학생 추가 2. 학생 정보 수정 3. 학생 조회");
	    	select = TuiReader.choice( 1,  3 );
	    	switch( select ) {
	    	case 1:
	    		System.out.println("수료자의 이름, 나이, 전화번호, 평가, 점수를 입력하세요");
	    		String studentString = TuiReader.readInput("정확한 정보를 입력하세요");
	    		String[] studentSplit = studentString.split("/");
	    		EducationStudent student = new EducationStudent();
	    		student.setName( studentSplit[0] );
	    		student.setAge( Integer.parseInt( studentSplit[1] ) );
	    		student.setPhone( studentSplit[2] );
	    		student.setExamination( studentSplit[3] );
	    		student.setStudentScore( Integer.parseInt( studentSplit[4] ) );
	    		student.setEducationID( selectedEducationID );
	    		Vector<EducationStudent> studentList = businessEducationTeam.getAllStudent();
	    		student.setStudentID( studentList.get(-1).getStudentID() + 1 );
	    		businessEducationTeam.setStudent( student );
	    		businessEducationTeam.manage( Target.EDUCATION_STUDENT, Crud.CREATE );
	    		System.out.println("저장이 완료되었습니다");
	    		break;
	    	}
    	}
    	/*
    	Vector<EducationStudent> studentList = businessEducationTeam.getStduentByEducation( selectedEducationID );
    	System.out.println( "--------------- 학생 번호를 선택하세요 -------------------");
    	for( EducationStudent student : studentList ) {
    		System.out.println( student.getStudentID() + ": " + student.getName() );
    	}
    	System.out.println( "----------------------------------------------------");
    	select = TuiReader.choice( studentList.get(0).getStudentID(), studentList.get(0).getStudentID() + studentList.size() );
    	*/
    }
    private static void manageEducation() {
    	System.out.println("추가할 교육 이름, 교육 기간, 교육 장소, 강사 이름, 강사 전화번호, 교육 예산, 교육 내용을 입력하세요.");		// 예산, 강사 전화번호 추가 & 교육 날짜, 교육 대상자 삭제
    	String educationString = TuiReader.readInput("정확한 정보를 입력하세요");
    	String[] educationSplit = educationString.split("/");
    	Education education = new Education();
    	education.setName( educationSplit[0] );
    	education.setDuration( Integer.parseInt( educationSplit[1] ) );
    	education.setPlace( educationSplit[2] );
    	education.setTeacherName( educationSplit[3] );
    	education.setTeacherPhoneNumber( educationSplit[4] );
    	education.setBudget( Integer.parseInt( educationSplit[5] ) );
    	education.setContent( educationSplit[6] );
    	int newEducationID = businessEducationTeam.getAllEducation().get(-1).getEducationID() + 1;
    	education.setEducationID( newEducationID );
    	System.out.println( "입력하신 정보가 올바르게 입력되었다면 확인을 눌러주세요.");
    	System.out.println( education.getName() + ": " + education.getTeacherName() + " " + education.getContent() );
    	System.out.println("1. 확인  2. 취소");
    	int choice = TuiReader.choice( 1,  2 );
    	if( choice==1 ) {
    		businessEducationTeam.setEducation( education );
        	businessEducationTeam.manage( Target.EDUCATION, Crud.CREATE );
        	System.out.println("새로운 교육이 추가되었습니다.");
    	}
    }
    private static void addContractManagementPolicy() {
    	Vector<ContractManagementPolicy> policyList = contractManagementTeam.getAllPolicy();
    	if( policyList.size()== 0 ) {
    		System.out.println("아직 수립된 정책이 없습니다.");
    	} else {
    		System.out.println("-----------현재 수립되어있는 정책 목록입니다. ------------------");
        	for( int i=0; i<policyList.size(); i++ ) {
        		System.out.println( i + ": " + policyList.get(i).getName() );
        	}
        	System.out.println("-------------------------------------------------------");
    	}
    	System.out.println("1. 확인  2. 지침 수립");
    	int choice = TuiReader.choice( 1,  2 );
    	if( choice==2 ) {
    		while( true ) {
    			System.out.println("지침 명칭, 지침 세부내용을 입력하세요.");
    			String policyString;
    			String[] policySplit;
    			ContractManagementPolicy newPolicy = new ContractManagementPolicy();
        		while( true ) {
        			policyString = TuiReader.readInput("");
            		policySplit = policyString.split("/");
            		newPolicy.setName( policySplit[0] );
            		newPolicy.setContent( policySplit[1] );
            		newPolicy.setPolicyID( policyList.size() );
            		int cnt = 0;
            		for( ContractManagementPolicy alreadyPolicy: policyList ) {
            			if( alreadyPolicy.getName().equals( newPolicy.getName() ) ) {
            				System.out.println("이미 같은 명칭을 가진 정책이 존재합니다.");
            				cnt=0;
            				break;
            			} else {
            				cnt++;
            			}
            		}
            		if( cnt==policyList.size()-1 ) break;
        		}
        		// 정책 이름 중복 확인
        		System.out.println("------------입력한 정보가 올바른지 확인해주세요.-----------------");
        		System.out.println("지침 명칭: " + newPolicy.getName() );
        		System.out.println("지침 내용: " + newPolicy.getContent() );
        		System.out.println("1. 확인  2. 취소");
        		choice = TuiReader.choice( 1,  2 );
        		if( choice==1 ) {
        			contractManagementTeam.setPolicy( newPolicy );
        			contractManagementTeam.manage( Target.CONTRACT_MANAGEMENT_POLICY, Crud.CREATE );
        			break;
        		}
    		}
    	}
    }
    private static void manageContractAnalysis() {
    	Vector<Insurance> insuranceList = contractManagementTeam.getAllInsurance();
    	if( insuranceList.size()==0 ) {
    		System.out.println("보험 상품 내역을 불러올 수 없습니다");
    	} else {
    		System.out.println("------------------ 현재 생성되어있는 보험 목록 -------------------------");
        	for( int i=0; i<insuranceList.size(); i++ ) {
        		Insurance tempInsurance = insuranceList.get(i);
        		Vector<Contract> contractListAboutInsurance = contractManagementTeam.getContractByInsuranceID( tempInsurance.getInsuranceID() );
        		int signedCustomerCount = contractListAboutInsurance.size();
        		System.out.println( i + ": " + tempInsurance.getInsuranceName() + " " + tempInsurance.getPayment() + " " + tempInsurance.getGuarantee() +
        				" " + tempInsurance.getRewardAmount() + " " + signedCustomerCount + "명의 가입자" );
        	}
        	System.out.println("----------------------------------------------------------------");
        	System.out.println("자세히 보고 싶은 보험을 선택하세요");
        	int choice = TuiReader.choice( 0, insuranceList.size() - 1 );
        	Insurance selectedInsurance = insuranceList.get( choice );
        	int signedCustomerCount=0;
        	int amountResult=0;
        	if( selectedInsurance.getSalesPerformance() == 0 ) {
        		System.out.println("아직 해당 상품에 대한 실적을 계산할 수 없습니다.");
        		return;
        	} else {
        		Vector<Contract> contractListAboutInsurance = contractManagementTeam.getContractByInsuranceID( selectedInsurance.getInsuranceID() );
        		signedCustomerCount = contractListAboutInsurance.size();
        		amountResult = signedCustomerCount * selectedInsurance.getPayment();
        	}
        	if( selectedInsurance.getResultAnalysis()==0 ) {
        		System.out.println("아직 효율 데이터를 계산할 수 없습니다.");
        		return;
        	}
        	System.out.println( "--------------" + selectedInsurance.getInsuranceName() + "에 대한 통계입니다. ---------------------" );
        	System.out.println( "예상 손익률: " + selectedInsurance.getEstimatedProfitRate() );
        	System.out.println( "목표 가입자수: " + selectedInsurance.getGoalPeopleNumber() );
        	System.out.println( "판매 실적: " + amountResult );
        	System.out.println( "분석 결과: " + selectedInsurance.getResultAnalysis() );
        	System.out.println("-----------------------------------------------------------------------------------------------");
    	}
    }
    private static void managePayment() {
    	Vector<Payment> paymentList = contractManagementTeam.getAllPayment();
    	if( paymentList.size()==0 ) {
    		System.out.println("현재 상품에 가입된 고객이 없습니다");
    		return;
    	}
    	int tmp = 0;
    	for( Payment payment : paymentList ) {
    		if( !payment.getResult() ) break;
    		else tmp++;
    	}
    	if( tmp==paymentList.size() ) {
    		System.out.println("현재 미납 대상자가 존재하지 않습니다.");
    		return;
    	}
    	System.out.println("----------------- 납부 대상자들 목록입니다 ----------------------");
    	int num = 0;
    	for( Payment payment : paymentList ) {
    		Contract tempContract = contractManagementTeam.getContract( payment.getContractID() );
    		Customer tempCustomer = contractManagementTeam.getCustomerInformation( tempContract.getCustomerID() );
    		Insurance tempInsurance = contractManagementTeam.getInsurance( tempContract.getInsuranceID() );
    		String payState;
    		if( payment.getResult() ) payState="납부";
    		else payState="미납부";
    		System.out.println( num + " " + tempCustomer.getName() + " " + tempCustomer.getAge() + " " +
    				tempInsurance.getInsuranceName() + " " + payment.getAmount() + "원 " + payment.getPayway().getString() + " " +
    				payment.getContractDuration() + " " + payState );
    		num++;
    	}
    	System.out.println( "---------------------------------------------------");
    	System.out.println("원하시는 고객을 선택하세요");
    	int choice = TuiReader.choice( 0, num-1 );
    	Payment selectedPayment = paymentList.get( choice );
    	Contract selectedContract = contractManagementTeam.getContract( selectedPayment.getContractDuration() );
    	Customer selectedCustomer = contractManagementTeam.getCustomerInformation( selectedContract.getCustomerID() );
		Insurance selectedInsurance = contractManagementTeam.getInsurance( selectedContract.getInsuranceID() );
		System.out.println( "------------------ 해당 고객 정보입니다 -----------------------");
		System.out.println("고객 번호: " + selectedCustomer.getCustomerID() );
		System.out.println("고객 성명: " + selectedCustomer.getName() );
		System.out.println("고객 나이: " + selectedCustomer.getAge() );				// 생년월일이 없어 나이로 대체
		System.out.println("해당 상품: " + selectedInsurance.getInsuranceName() );
		int unPayedMonth = selectedPayment.getContractDuration() - selectedPayment.getDuration();
		int unPayed = unPayedMonth * selectedPayment.getAmount();
		System.out.println("미납 금액: " + unPayed );
		System.out.println("수납 방법: " + selectedPayment.getPayway().getString() );
		System.out.println("가입 기간: " + selectedPayment.getContractDuration() );
		System.out.println("미납 기간: " + unPayedMonth );
		System.out.println("미납 안내: " + selectedPayment.getContent() );
		System.out.println("----------------------------------------------------------");
		System.out.println("1. 미납 공지  2. 확인");
		choice = TuiReader.choice(1,  2 );
		if( choice==1 ) {
			// 미납 안내 문구
			String unpayedWarning = "현재 보험료가 미납되었습니다. 빠른 시일 내에 납부 부탁드립니다.";
			selectedPayment.setContent(unpayedWarning);
			contractManagementTeam.setPayment(selectedPayment);
			contractManagementTeam.manage( Target.PAYMENT, Crud.UPDATE );
			System.out.println("미납 공지가 완료되었습니다.");
		}
    }
    private static void manageExpireContract() {
    	List<Contract> contractList = contractManagementTeam.getAllContract();
    	if( contractList.size()==0 ) {
    		System.out.println("현재 상품에 가입된 사용자가 없습니다.");
    		return;
    	}
    	LocalDate today = LocalDate.now();
    	List<Contract> expireList = new Vector<>();
    	for( Contract contract : contractList ) {
    		Insurance tempInsurance = contractManagementTeam.getInsurance( contract.getInsuranceID() );
    		if( today.isAfter( contract.getContractDate().plusMonths( tempInsurance.getDuration() ) ) ) expireList.add( contract );
    	}
    	if( expireList.size()==0 ) {
    		System.out.println("만기 대상자가 없습니다");
    		return;
    	}
    	System.out.println("---------------------계약이 만기된 고객 목록입니다.-------------------------");
    	int i = 0;
    	for( Contract contract: expireList ) {
    		Customer tmpCustomer = contractManagementTeam.getCustomerInformation( contract.getCustomerID() );
    		Insurance tmpInsurance = contractManagementTeam.getInsurance( contract.getInsuranceID() );
    		System.out.println( i + " " + tmpCustomer.getName() + " " + tmpInsurance.getInsuranceName() + " " + contract.getContractDate()+ " " +
    				tmpInsurance.getDuration() + "달 " );
    		i++;
    	}
    	System.out.println("--------------------------------------------------------------------");
    	int choice = TuiReader.choice( 0, i-1 );
    	Contract selectedContract = expireList.get( choice );
    	System.out.println("1. 안내장 발송  2. 확인");
    	choice = TuiReader.choice(1,  2 );
    	if( choice==1 ) {
    		AdviceNote adviceNote = new AdviceNote();
    		int newID = contractManagementTeam.getAllAdviceNote().size();
    		adviceNote.setAdviceNoteID( newID );
    		adviceNote.setResult( Result.PROCESS );
    		adviceNote.setCustomerID( selectedContract.getCustomerID() );
    		adviceNote.setContractID( selectedContract.getContractID() );
    		Insurance tmpInsurance = contractManagementTeam.getInsurance( selectedContract.getInsuranceID() );
    		String content = tmpInsurance.getInsuranceName() + "에 대한 계약이 만료되었습니다. 재계약 여부를 확인해주세요. ";
    		adviceNote.setContent( content );
    		contractManagementTeam.setAdviceNote(adviceNote);
    		contractManagementTeam.manage( Target.ADVICE_NOTE, Crud.CREATE );
    		System.out.println("안내장 발송이 완료되었습니다.");
    	}
    	// 안내장 발송까지 완료
    }
    private static void businessTeamManage() {
        System.out.println("********************* 영업 조직 관리 *********************");
        System.out.println(" 1. 운영 방침");
        System.out.println(" 2. 성과 평가");
        int campaignChoice = TuiReader.choice(1, 2);
        switch(campaignChoice) {
            case 1:
                operationPolicy();
                break;
            case 2:
                evaluationMenu();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    private static void operationPolicy() {
        System.out.println("********************* 운영 방침 *********************");
        System.out.println(" 1. 운영 방침 열람");
        System.out.println(" 2. 운영 방침 수립");
        System.out.println(" 3. 메인 메뉴로 돌아가기");
        int choice = TuiReader.choice(1, 3);
        switch (choice) {
            case 1:
                retrieveOPPolicy();
                operationPolicy();
                break;
            case 2:
                createOPPolicy();
                operationPolicy();
                break;
            case 3:
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    private static void createOPPolicy() {
        System.out.println("********************* 운영 방침 수립 *********************");
        System.out.println("'운영 방침 제목 / 운영 방침 내용'를 입력해주세요.");
        businessTeam.establishPolicy(Target.OPERATION_POLICY, Crud.CREATE);
        System.out.println("인수 정책이 저장되었습니다.");
    }

    private static void retrieveOPPolicy() {
        System.out.println("********************* 운영 방침 열람 *********************");
        List<OperationPolicy> policyList = businessTeam.getAllPolicy();
        if(policyList != null) {
            for (int i = 0; i < policyList.size(); i++) {
                System.out.println(i + ". " + policyList.get(i).getPolicyID() + " " + policyList.get(i).getName());
                System.out.println("운영 방침 내용: " + policyList.get(i).getContent());
            }
        } else {
            System.out.println("현재 운영 방침이 존재하지 않습니다.");
        }
    }
    private static void evaluationMenu() {
        System.out.println("********************* 성과 평가 *********************");
        System.out.println(" 1. 판매 조직 목록");
        System.out.println(" 2. 성과 평가");
        System.out.println(" 3. 메인 메뉴로 돌아가기");
        int choice = TuiReader.choice(1, 3);
        switch (choice) {
            case 1:
                retrieveEvaluation();
                evaluationMenu();
                break;
            case 2:
                updateEvaluation();
                evaluationMenu();
                break;
            case 3:
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }

    }
    private static void updateEvaluation() {
        System.out.println("********************* 성과 평가 *********************");
        System.out.println("'팀 이름 / 성과'를 입력해주세요.");
        businessTeam.evaluateResult();
        System.out.println("평가 내용을 저장되었습니다.");
    }
    private static void retrieveEvaluation() {
        System.out.println("********************* 판매 조직 조회 *********************");
        List<SellGroup> sellGroupList = sellGroupTeam.getAllGroup();
        if(sellGroupList != null) {
            for (int i = 0; i < sellGroupList.size(); i++) {
                System.out.println(sellGroupList.get(i).getGroupID() + " . " +
                    sellGroupList.get(i).getExResult()+" / "+
                    sellGroupList.get(i).getName()+" / "+
                    sellGroupList.get(i).getRepresentative()+" / "+
                    sellGroupList.get(i).getRepresentativePhoneNumber());
            }
        } else {
            System.out.println("현재 판매 조직이 존재하지 않습니다.");
        }
    }
}
