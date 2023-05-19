import java.util.Date;
import java.util.Vector;

import Contract.Contract;
import Customer.Customer;
import Reward.Reward;
import Teams.InsuranceDevelopmentTeam;
import Teams.RewardTeam;
import exception.CustomException;
import insurance.Insurance;
import util.Constants;
import util.Constants.Crud;
import util.Constants.Result;
import util.Constants.Target;
import util.TuiReader;

public class Main {
    private static InsuranceDevelopmentTeam insuranceDevelopmentTeam;

    public static void initialize() {
        insuranceDevelopmentTeam = new InsuranceDevelopmentTeam();
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
                    case 8:
                    	processReward();
                        break;
                    case 9:
                    	applyReward();
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
                insuranceDevelopmentTeam.plan(1, 1);
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
        if (choice2 == 1) {insuranceDevelopmentTeam.plan(1, 3);} 
        else if (choice2 == 2) {insuranceDevelopmentTeam.plan(1, 4);} 
    }
    private static void printCreateInsuranceMenu() {
        System.out.println("********************* 상품 개발 *********************");
        System.out.println(" 1. 상품 기획 버튼");
        System.out.println(" 2. 상품 개발 버튼");
    }
    private static void applyReward() {
    	// 고객 ID를 어떻게 가져오는가...
    	int customerID = 0;
    	RewardTeam rewardTeam = new RewardTeam();
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
    	int selectedContract = TuiReader.choice();
    	int selectedInsuranceID = assignedInsurances.get(selectedContract).getInsuranceID();
    	System.out.println("신청을 위해서 다음 정보가 필요합니다.");
    	// 신청 조건이 기입되어 있는 곳이 없음...
    	//System.out.println("신청 조건: " + assignedInsurances.get(choice).get);
    	System.out.println("사고 증빙 서류: 사고 증명서, 사진 등");
    	System.out.println("본인 증빙 서류: 주민등록증 사본, 가족관계증명서 등");
    	System.out.println("위 내용을 확인하셨다면 '확인'버튼을 누르세요.");
    	System.out.println("1. 확인");
    	int choice = TuiReader.choice();
    	System.out.println("신청 내용을 입력하고 증빙 서류를 첨부해주세요.");
    	String userInput = TuiReader.readInput(null);
    	String[] inputList = userInput.split("/");
    	System.out.println( "1. 신청     2. 취소");
    	choice = TuiReader.choice();
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
    
    
    private static void printMenu() {
        System.out.println("********************* MENU *********************");
        System.out.println(" 1. 상품개발");
        System.out.println(" 8. 보상 처리");
        System.out.println(" 9. 보험금 신청");
        System.out.println(" 0. 종료 ");
    }
    @SuppressWarnings("deprecation")
	private static void processReward() {
    	RewardTeam rewardTeam = new RewardTeam();
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
	    	int select = TuiReader.choice();
	    	Reward selectedReward = rewardList.get( select );
	    	System.out.println( selectedReward.getCustomerName() + " - " + selectedReward.getContent() );
	    	System.out.println( "해당 보험에 대해 승인하시겠습니까?" );
	    	System.out.println( "1. 보상 지급   2. 요청 거절" );
	    	int resultSelect = TuiReader.choice();
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
}
