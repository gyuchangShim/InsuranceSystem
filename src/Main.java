import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Contract.Contract;
import Contract.ContractList;
import Contract.ContractListImpl;
import Dao.CampaignProgramDao;
import MarketingPlanning.CampaignProgram;
import MarketingPlanning.CampaignProgramList;
import MarketingPlanning.CampaignProgramListImpl;
import Reward.Reward;
import Teams.*;
import Undewriting.AssumePolicy;
import Undewriting.AssumePolicyList;
import Undewriting.AssumePolicyListImpl;
import exception.CustomException;
import insurance.Insurance;
import insurance.InsuranceList;
import insurance.InsuranceListImpl;
import util.Constants;
import util.Constants.Target;
import util.Constants.Crud;
import util.TuiReader;


public class Main {
    private static InsuranceDevelopmentTeam insuranceDevelopmentTeam;
    private static UnderwritingTeam underwritingTeam;
    private static InsuranceList insuranceList;
    private static AssumePolicyList assumePolicyList;
    private static MarketingPlanningTeam marketingPlanningTeam;
    private static CampaignProgram campaignProgram;
    private static CampaignProgramList campaignProgramList;
    private static CampaignProgramDao campaignProgramDao;
    private static Contract contract;
    private static ContractList contractList;

    public static void initialize() {
        insuranceDevelopmentTeam = new InsuranceDevelopmentTeam();
        marketingPlanningTeam = new MarketingPlanningTeam();
        insuranceList = new InsuranceListImpl();
        assumePolicyList = new AssumePolicyListImpl();
        underwritingTeam = new UnderwritingTeam(assumePolicyList);
        campaignProgram = new CampaignProgram();
        campaignProgramList = new CampaignProgramListImpl();
        campaignProgramDao = new CampaignProgramDao();
        contract = new Contract();
        contractList = new ContractListImpl();

        // 날짜 불러오기
        LocalDate today = LocalDate.now();
    }



    public static void main(String[] args) {
        initialize();
        try {
            while (true) {
                printMenu();
                int choice = TuiReader.choice();
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
                        // 보험 가입 -> Contract에 저장
                        registerInsurance();
                    case 5:
                        // 캠페인 프로그램 관련
                        campaignProgramMenu();
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
            }
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void createInsurance() {
        printCreateInsuranceMenu();
        int choice = TuiReader.choice();
        switch (choice) {
            case 1:
                createInsurancePlan();
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
                insuranceDevelopmentTeam.plan(Constants.Target.INSURANCE, Constants.Crud.CREATE);
                break;
            case 2:
                managePlan();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
    private static void managePlan() {
        System.out.println("수정: 1, 삭제: 2");
        int choice2 = TuiReader.choice();
        while (choice2 != 1 && choice2 != 2) {
            System.out.println("정확히 선택해주세요.");
            choice2 = TuiReader.choice();
        }
        if (choice2 == 1) {insuranceDevelopmentTeam.plan(Constants.Target.INSURANCE, Constants.Crud.UPDATE);}
        else if (choice2 == 2) {insuranceDevelopmentTeam.plan(Constants.Target.INSURANCE, Constants.Crud.DELETE);}
    }
    private static void printCreateInsuranceMenu() {
        System.out.println("********************* 상품 개발 *********************");
        System.out.println(" 1. 상품 기획 버튼");
        System.out.println(" 2. 상품 개발 버튼");
    }
    private static void printMenu() {
        System.out.println("********************* MENU *********************");
        System.out.println(" 1. 상품개발");
        System.out.println(" 2. 인수정책");
        System.out.println(" 3. 인수심사");
        System.out.println(" 4. 보험가입");
        System.out.println(" 5. 캠페인 프로그램");
        System.out.println(" 8. 보상 처리");
        System.out.println(" 0. 종료 ");
    }

    private static void registerInsurance() {
        // 보험을 가입하다 usecase 부분
        System.out.println("********************* 보험 가입 *********************");
        List<Insurance> registList = insuranceList.getAllInsurance();
        // 고객이 가입할 보험 리스트 출력
        for (int i = 0; i < registList.size(); i++) {
            System.out.println(i + ". " + registList.get(i).getInsuranceName() + " " + registList.get(i).getPayment()
                        + " " + registList.get(i).getDuration());
        }
        // 고객이 가입할 보험 클릭
        int registChoice = TuiReader.choice();
        while (registChoice < 0 || registChoice >= registList.size()) {
            System.out.println("정확히 선택해주세요.");
            registChoice = util.TuiReader.choice();
        }
        contract.setInsuranceID(registList.get(registChoice).getInsuranceID());
        contract.setContractDate(LocalDateTime.now());
        // TODO: 바꿔줘야함
        contract.setContractFile("최혁");
        // 보험 가입 유스케이스 6번 시나리오 - 사용자의 동의 부분 X
        System.out.println(contract);
        System.out.println("1. 신청");
        System.out.println("2. 취소");
        int contractChoice = TuiReader.choice();
        switch(contractChoice) {
            case 1:
                contractList.add(contract);
                System.out.println("보험 가입 신청이 완료되었습니다.");
                break;
            case 2:
                printMenu();
                break;
        }
    }

    private static void uwPolicy() {
        System.out.println("********************* 인수 정책 *********************");
        System.out.println(" 1. 인수 정책 열람");
        System.out.println(" 2. 인수 정책 수립");
        System.out.println(" 3. 인수 정책 삭제");
        int uwchoice = TuiReader.choice();
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
        int inChoice = TuiReader.choice();
        while (inChoice < 0 || inChoice >= registList.size()) {
            System.out.println("정확히 선택해주세요.");
            inChoice = util.TuiReader.choice();
        }
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
        int campaignChoice = TuiReader.choice();
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
        // 캠페인 프로그램 attribute가 진행 전/중/후 구분 + 중, 후 캠페인 리스트 출력
        System.out.println("◈ 현재 진행중인 캠페인");
        List<CampaignProgram> runcampaignPrograms = campaignProgramList.retrieveRun();
        for(CampaignProgram campaignProgram : runcampaignPrograms) {
            System.out.println(campaignProgram.getCampaignID() + campaignProgram.getCampaignName());
        }
        System.out.println(" 1. 새로운 캠페인 프로그램 실행");
        System.out.println(" 2. 캠페인 기획안 수정");
        System.out.println(" 3. 지난 캠페인");
        int campaignChoice = TuiReader.choice();
        switch(campaignChoice) {
            case 1:
                // 캠페인 프로그램 실행
                runCampaign();
                break;
            case 2:
                // 캠페인 기획안 수정
                modifyCampaignProgramPlan();
                break;
            case 3:
                endCampaign();
        }

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
        int campaignChoice = TuiReader.choice();
        System.out.println("실행할 캠페인 프로그램을 선택하세요.");

        // 선택된 campaign 실행 or 취소
        System.out.println("선택한 기획안 출력");
        System.out.println(" 1. 프로그램 실행");
        System.out.println(" 0. 초기 화면으로");
        int runChoice = TuiReader.choice();
        switch (runChoice) {
            case 1:
                System.out.println("해당 프로그램이 실행됩니다.");
                // 선택된 프로그램 상태를 진행 중으로 변환
                campaignProgram.setProgramState(CampaignProgram.campaignState.Run);
                break;
            case 2:
                printMenu();
        }
    }

    private static void modifyCampaignProgramPlan() {
        System.out.println("********************* 캠페인 프로그램 기획안 수정 *********************");
        // 캠페인 프로그램 진행 전, 중 상태의 기획안만 수정 - 시나리오 X
    }

    private static void endCampaign() {
        // 지난 캠페인
        System.out.println("********************* 지난 캠페인 페이지 *********************");
        ArrayList<CampaignProgram> campaignPrograms = campaignProgramList.retrieveAllEnd();
        for(CampaignProgram campaignProgram : campaignPrograms) {
            System.out.println(campaignProgram.getCampaignID() + campaignProgram.getCampaignName());
        }
        int endCampaignChoice = TuiReader.choice();


    }

    @SuppressWarnings("deprecation")
	private static void processReward() {
    	RewardTeam rewardTeam = new RewardTeam();
    	Vector<Reward> rewardList = rewardTeam.getAllReward();
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
	    	int select = TuiReader.choice();
	    	Reward selectedReward = rewardList.get( select );
	    	System.out.println( selectedReward.getCustomerName() + " - " + selectedReward.getContent() );
	    	System.out.println( "해당 보험에 대해 승인하시겠습니까?" );
	    	System.out.println( "1. 보상 지급   2. 요청 거절" );
	    	int resultSelect = TuiReader.choice();
	    	if( resultSelect == 1 ) {
	    		// 승인
	    		System.out.println( selectedReward.getCustomerName() );
	    		System.out.println( "지급 금액: " + Integer.toString( selectedReward.getReward() ) + "원" );
	    		System.out.println( "책임자 이름: 사용자" );
	    		System.out.println( "내용: " + selectedReward.getContent() );
	    		System.out.println( "--------- 해당 요청에 대한 보상을 지급합니다. -----------");
	    		
	    		rewardTeam.rewardResult( selectedReward.getRewardID(), Constants.Result.ACCEPT );
	    	} else if( resultSelect == 2 ) {
	    		// Alter2
	    		System.out.print( "보상 거절 사유를 입력하세요: ");
	    		String reason = TuiReader.readInput( "정확한 사유를 입력하세요" );
	    		selectedReward.setContent( reason );   		// DENY된 보험 신청의 경우, Content에 거절 사유를 남긴다.
	    		rewardTeam.rewardResult( selectedReward.getRewardID(), Constants.Result.DENY );
	    	} else {
	    		// 
	    	}
    	}  
    }
}
