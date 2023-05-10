import Teams.InsuranceDevelopmentTeam;

import exception.CustomException;
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
                    case 2:

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
        if (choice2 == 1) {
            insuranceDevelopmentTeam.plan(1, 3);
        } else if (choice2 == 2) {
            insuranceDevelopmentTeam.plan(1, 4);
        }
    }

    private static void printCreateInsuranceMenu() {
        System.out.println("********************* 상품 개발 *********************");
        System.out.println(" 1. 상품 기획 버튼");
        System.out.println(" 2. 상품 개발 버튼");
    }

    private static void printMenu() {
        System.out.println("********************* MENU *********************");
        System.out.println(" 1. 상품개발");
        System.out.println(" 0. 종료 ");
    }

}
