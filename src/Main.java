import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import businessEducation.Education;
import businessEducation.EducationStudent;
import contract.AdviceNote;
import contract.Contract;
import contract.ContractList;
import contract.ContractListImpl;
import contract.Payment;
import contractManagement.ContractManagementPolicy;
import customer.Customer;
import exception.CIllegalArgumentException;
import exception.CInsuranceNotFoundException;
import exception.CustomException;
import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceListImpl;
import insurance.InsuranceState;
import insurance.InsuranceType;
import marketingPlanning.CampaignProgram;
import marketingPlanning.CampaignProgramList;
import marketingPlanning.CampaignProgramListImpl;
import outerActor.OuterActor;
import reward.Reward;
import teams.BusinessEducationTeam;
import teams.ContractManagementTeam;
import teams.CustomerManagementTeam;
import teams.InsuranceDevelopmentTeam;
import teams.MarketingPlanningTeam;
import teams.UnderwritingTeam;
import undewriting.AssumePolicy;
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
    private static UserPersonaList userPersonaList;
    private static InsuranceDevelopmentTeam insuranceDevelopmentTeam;
    private static UnderwritingTeam underwritingTeam;
    private static MarketingPlanningTeam marketingPlanningTeam;
    private static CustomerManagementTeam customerManagementTeam;
    private static BusinessEducationTeam businessEducationTeam;
    private static ContractManagementTeam contractManagementTeam;
    private static int customerID;

    public static void initialize() {
        insuranceList = new InsuranceListImpl();
        assumePolicyList = new AssumePolicyListImpl();
        campaignProgramList = new CampaignProgramListImpl();
        contractList = new ContractListImpl();
        userPersonaList = new UserPersonaListImpl();
        insuranceDevelopmentTeam = new InsuranceDevelopmentTeam(insuranceList);
        marketingPlanningTeam = new teams.MarketingPlanningTeam();
        underwritingTeam = new teams.UnderwritingTeam(assumePolicyList);
        customerManagementTeam = new teams.CustomerManagementTeam();
        businessEducationTeam = new teams.BusinessEducationTeam();
        contractManagementTeam = new teams.ContractManagementTeam();
    }
    public static void main(String[] args) {
        initialize();
        loginPage();
        customerNotice();
        mainPage();
    }
    private static void loginPage(){
        System.out.println("*********************  로그인  *********************");
        System.out.println(" 1. 고객 로그인");
        System.out.println(" 3. 회원가입");

        int choice = TuiReader.choice(1, 3);
        switch (choice) {
            case 1 :
                System.out.println("ID:");
                String userId = TuiReader.readInput("정확히 입력해주세요.");
                System.out.println("PassWord:");
                String password = TuiReader.readInput("정확히 입력해주세요.");
                ResultSet resultSet = customerManagementTeam.login(userId,password);
                try { while (resultSet.next()){customerID = resultSet.getInt("customerID");}
                } catch (SQLException e) {throw new RuntimeException(e);}
                break;
            case 3 :
                System.out.println("ID:");
                userId = TuiReader.readInput("정확히 입력해주세요.");
                System.out.println("PassWord:");
                password = TuiReader.readInput("정확히 입력해주세요.");
                System.out.println("고객정보를 '/'로 구분하여 입력해주세요 : " +
                    "address/age/job/name/phoneNumber/registrationNumber/salaryPercentage");
                String customerInf = TuiReader.readInput("정확히 입력해주세요.");
                customerManagementTeam.join(userId,password,customerInf);
            default :
                customerID=0;

        }
    }
    private static void customerNotice() {
    	// 최초 로그인 시 자신의 계정에 와있는 알림을 확인한다.
    	if( customerID != 0 ) {
    		checkAdviceNote();
    	}
    	
    }
    private static void mainPage() {
        while (true) {
            try {
            	if( customerID==0 ) {
            		printEmployeeMenu();
            	} else {
            		printCustomerMenu();
            	}
                int choice = TuiReader.choice(0, 45);
                switch (choice) {
                    case 21:
                        createInsurance();
                        break;
                    case 22:
                        // 인수 정책 관련 CRUD
                        uwPolicy();
                        break;
                    case 23:
                        // 인수 심사 -> Contract 불러오기
                        underWriting();
                        break;
                    case 11:
                        // 보험 가입 -> Contract에 저장
                        registerInsurance();
                    case 24:
                        // 캠페인 프로그램 관련
                        campaignProgramMenu();
                    case 25:
                        processSales();
                        break;
                    case 26:
                        processReward();
                        break;
                    case 12:
                        applyReward();
                        break;
                    case 27:
                    	managePayment();
                    	break;
                    case 28:
                    	manageStudent();
                    	break;
                    case 29:
                    	manageEducation();
                    	break;
                    case 30:
                    	manageExpireContract();
                    	break;
                    case 31:
                    	addContractManagemetPolicy();
                    	break;
                    case 32:
                    	manageContractAnalysis();
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
    private static void printCustomerMenu() {
    	System.out.println("********************* MENU *********************");
    	System.out.println("11. 보험 가입");
    	System.out.println("12. 보험금 신청");
    	System.out.println(" 0. 종료 ");
    }
    private static void printEmployeeMenu() {
    	System.out.println("********************* MENU *********************");
    	System.out.println(" 21. 상품개발");
        System.out.println(" 22. 인수정책");
        System.out.println(" 23. 인수심사");
        System.out.println(" 24. 캠페인 프로그램");
        System.out.println(" 25. 영업 활동");
        System.out.println(" 26. 보상 처리");
        System.out.println(" 27. 분납/수금 관리");
        System.out.println(" 28. 교육 수료자 관리");
        System.out.println(" 29. 교육 추가");
        System.out.println(" 30. 만기 계약 관리");
        System.out.println(" 31. 계약 관리 지침");
        System.out.println(" 32. 계약 통계 관리");
        System.out.println(" 0. 종료 ");
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
        List<Insurance> registList = insuranceList.retrieveAll();
        // 고객이 가입할 보험 리스트 출력
        for (int i = 0; i < registList.size(); i++) {
            System.out.println(i + ". " + registList.get(i).getInsuranceName() + " " + registList.get(i).getPayment()
                + " " + registList.get(i).getDuration());
        }
        // 고객이 가입할 보험 클릭
        int registChoice = TuiReader.choice(0, registList.size() - 1);
        Contract contract = new Contract();
        contract.setInsuranceID(registList.get(registChoice).getInsuranceID());
        contract.setContractDate(LocalDate.now());
        // TODO: 바꿔줘야함
        contract.setContractFile("최혁");
        // 보험 가입 유스케이스 6번 시나리오 - 사용자의 동의 부분 X
        System.out.println(contract);
        System.out.println("1. 신청");
        System.out.println("2. 취소");
        int contractChoice = TuiReader.choice(1, 2);
        switch(contractChoice) {
            case 1:
                contractList.add(contract);
                System.out.println("보험 가입 신청이 완료되었습니다.");
                break;
            case 2:
                printCustomerMenu();
                break;
        }
    }
    private static void uwPolicy() {
        System.out.println("********************* 인수 정책 *********************");
        System.out.println(" 1. 인수 정책 열람");
        System.out.println(" 2. 인수 정책 수립");
        System.out.println(" 3. 인수 정책 삭제");
        int uwchoice = TuiReader.choice(1, 3);
        switch (uwchoice) {
            case 1:
                retrieveUWPolicy();
                break;
            case 2:
                createUWPolicy();
                break;
            case 3:
                deleteUWPolicy();
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
    private static void createUWPolicy() {
        System.out.println("********************* 인수 정책 수립 *********************");
        System.out.println("인수 정책 제목 / 인수 정책 내용 / 인수 정책 종류 : ");
        underwritingTeam.establishPolicy(Constants.Target.ASSUME_POLICY, Constants.Crud.CREATE);
        System.out.println("인수 정책이 저장되었습니다.");
    }
    private static void retrieveUWPolicy() {
        System.out.println("********************* 인수 정책 열람 *********************");
        //underwritingTeam.establishPolicy(0, 2);
        // 여기서 interface class에 함수를를 만들어야 하는지?
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
    private static void deleteUWPolicy() {
        System.out.println("********************* 인수 정책 열람 *********************");
        System.out.println("삭제할 인수 정책의 번호를 입력해주세요.");
        underwritingTeam.establishPolicy(Constants.Target.ASSUME_POLICY, Constants.Crud.DELETE);
        System.out.println("해당 인수 정책을 삭제하였습니다.");
    }
    private static void underWriting() {
        // 인수 심사하다 usecase 부분
        System.out.println("********************* 인수 심사 *********************");
        // 인수 심사 대상 리스트 출력
        List<Contract> registList = contractList.getAllregist();
        if(registList != null) {
            for (int i = 0; i < registList.size(); i++) {
                System.out.println(i + ". " + registList.get(i).getContractID()
                    + " " + registList.get(i).getCustomerID() + " " + registList.get(i).getContractID());
            }
        } else {
            System.out.println("현재 인수 심사 대상이 존재하지 않습니다.");
        }
        int inChoice = TuiReader.choice(0, registList.size() - 1);
        if(registList.get(inChoice).getContractID() != 0) {
            // 인수 심사 시작
        } else {
            // 공동 인수 유스케이스로 넘어감
            collaborateUW(registList.get(inChoice).getContractID());
        }
    }
    private static void collaborateUW(int colloborateContractId) {
        // 공동 인수 시작
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
//                retrieveCampaignProgram();
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
    	Date date = new Date();
    	applyReward.setAppliDate( date );
    	applyReward.setReward(0);
    	applyReward.setContractID( assignedContract.get( selectedContract ).getContractID() );
    	Customer tmpCustomer = rewardTeam.getCustomerInformation( customerID );
    	applyReward.setCustomerName( tmpCustomer.getName() );
    	rewardTeam.setReward( applyReward );
    	rewardTeam.manage( Target.REWARD, Crud.UPDATE );
    }
    private static void runCampaign() {
        // 캠페인 프로그램 실행 유스케이스 시나리오의 8번 제외 - 외부 Actor 배제
        System.out.println("********************* 캠페인 프로그램 실행 *********************");
        // 통과된 기획 리스트 보여주기 - DB
        List<CampaignProgram> campaignPrograms = campaignProgramList.retrieveAll();
        for(CampaignProgram campaignProgram : campaignPrograms) {
            System.out.println(campaignProgram.getCampaignID() + campaignProgram.getCampaignName());
        }
        // 실행할 campaignProgram 선택
        int campaignChoice = TuiReader.choice(0, campaignPrograms.size() - 1);
        System.out.println("실행할 캠페인 프로그램을 선택하세요.");

        // 선택된 campaign 실행 or 취소
        System.out.println("선택한 기획안 출력");
        System.out.println(" 1. 프로그램 실행");
        System.out.println(" 0. 초기 화면으로");
        int runChoice = TuiReader.choice(0, 1);
        switch (runChoice) {
            case 1:
                System.out.println("해당 프로그램이 실행됩니다.");
                // 선택된 프로그램 상태를 진행 중으로 변환
                CampaignProgram campaignProgram = campaignPrograms.get(campaignChoice);
                campaignProgram.setProgramState(CampaignProgram.campaignState.Run);
                break;
            case 2:
                printEmployeeMenu();
        }
    }
    private static void modifyCampaignProgramPlan() {
        System.out.println("********************* 캠페인 프로그램 기획안 수정 *********************");
        // 캠페인 프로그램 진행 전, 중 상태의 기획안만 수정 - 시나리오 X
    }
    private static void endCampaign() {
        // 지난 캠페인
        System.out.println("********************* 지난 캠페인 페이지 *********************");
        // TODO(혁): 지난 캠페인만 filter로 걸러내기
        List<CampaignProgram> campaignPrograms = campaignProgramList.retrieveAll();
        for(CampaignProgram campaignProgram : campaignPrograms) {
            System.out.println(campaignProgram.getCampaignID() + campaignProgram.getCampaignName());
        }
        int endCampaignChoice = TuiReader.choice(0, campaignPrograms.size() - 1);


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
                    " " + Integer.toString( rewardList.get(i).getAppliDate().getMonth() ) + "월 " +
                    Integer.toString( rewardList.get(i).getAppliDate().getDay() ) + "일 " );
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

    }
    private static void faceToFaceConsultation() {
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
    private static void addContractManagemetPolicy() {
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
    	Vector<Contract> contractList = contractManagementTeam.getAllContract();
    	if( contractList.size()==0 ) {
    		System.out.println("현재 상품에 가입된 사용자가 없습니다.");
    		return;
    	}
    	LocalDate today = LocalDate.now();
    	Vector<Contract> expireList = new Vector<Contract>();
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
    // 로그인 시 확인할 것
    private static void checkAdviceNote() {
    	Vector<AdviceNote> adviceList = contractManagementTeam.getAllAdviceNote();
    	Vector<AdviceNote> myAdviceNote = new Vector<AdviceNote>();
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
