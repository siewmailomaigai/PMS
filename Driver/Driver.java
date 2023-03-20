/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Driver;

import AccountExecutive.LoginAcc;
import AdminExecutive.ComplaintManagement;
import AdminExecutive.EmployeeManagement;
import AdminExecutive.FacilityBooking;
import AdminExecutive.FacilityManagement;
import AdminExecutive.ResidentTenantManagement;
import AdminExecutive.VendorManagement;
import Resident_Tenant.ComplaintRT;
import Resident_Tenant.FacilityBookingRT;
import Resident_Tenant.LoginRT;
import Resident_Tenant.ResidentProfile;
import Resident_Tenant.VisitorRT;
import SecurityGuard.LoginSG;
import Vendor.ComplaintVendor;
import Vendor.LoginVendor;
import Vendor.VendorProfile;
import Visitor.Visitor;
import AccountExecutive.IssueInvoice;
import AccountExecutive.OutstandingFee;
import AccountExecutive.PaymentCheck;
import AdminExecutive.LoginAdmin;
import BuildingExecutive.ComplaintStatus;
import BuildingExecutive.LoginBE;
import BuildingManager.UserManagement;
import BuildingManager.ViewBM;
import BuildingManager.Budget;
import BuildingExecutive.EmployeeTaskManager;
import BuildingExecutive.PatrollingandCheckpoint;
import BuildingExecutive.ViewBE;
import BuildingManager.LoginBM;
import BuildingManager.TeamStructureManagement;
import Resident_Tenant.MakePaymentRT;
import Resident_Tenant.ViewRT;
import SecurityGuard.CheckIn;
import SecurityGuard.RecordIncident;
import SecurityGuard.RecordVisitor;
import SecurityGuard.SearchViewVisitor;
import Vendor.MakePaymentVendor;
import Vendor.ViewVendor;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Hong Shen
 */
public class Driver {

    public static void main(String arg[]) throws IOException {

        Scanner scan = new Scanner(System.in);
        int cont;
        do {
            System.out.println("""
    1. Building Manager
    2. Account Executive
    3. Admin Executive
    4. Building Executive
    5. Resident/Tenant
    6. Vendor
    7. Security Guard
    8. Visitor
    Select your user type:
    """);
            int choice = scan.nextInt();
            switch (choice) {
                /* Building Manager */
                case 1 -> {
                    LoginBM login = new LoginBM("BuildingManager.txt");
                    login.LoginBM();
                    boolean backToMain = false;
                    do {
                        System.out.println("""
                1. User Management
                2. View report
                3. Budget planning
                4. Team Structure Management
                5. Logout
                Select your choice:""");
                        int option = scan.nextInt();
                        switch (option) {
                            case 1 -> {
                                UserManagement user = new UserManagement();
                                user.run();
                            }
                            case 2 -> {
                                ViewBM report = new ViewBM();
                                report.processOption(option);
                            }
                            case 3 -> {
                                Budget bd = new Budget();
                                bd.run();
                            }
                            case 4 -> {
                                TeamStructureManagement tsm = new TeamStructureManagement();
                                tsm.run();
                            }
                            case 5 -> {
                                System.out.println("Logout successful!");
                                backToMain = true;
                                break; // Return to user type selection
                            }
                            default -> {
                                System.out.println("Invalid input!");
                                continue; // Go back to menu options
                            }
                        }

                        if (backToMain) {
                            break;
                        }
                    } while (true);
                }

                /* Account Executive */
                case 2 -> {
                    LoginAcc Login = new LoginAcc("accountE.txt");
                    Login.LoginAcc();
                    boolean backToMain = false;
                    do {
                        System.out.println("""
                               1. Issue invoice, receipt and statement
                               2. Record, view, update payment
                               3. Add outstanding fee
                               4. View outstanding fee
                               5. Logout
                               Select your choice:""");
                        int option = scan.nextInt();
                        switch (option) {
                            case 1 -> {
                                IssueInvoice invoice = new IssueInvoice();
                                invoice.runInvoice();
                                return;
                                
                            }
                            case 2 -> {
                                PaymentCheck pay = new PaymentCheck();
                                pay.paymentCheck();
                          
                            }
                            case 3 -> {
                                OutstandingFee out = new OutstandingFee();
                                out.addOutstanding();

                            }
                            case 4 -> {
                                OutstandingFee out = new OutstandingFee();
                                out.viewOutstanding();
                          
                            }
                            case 5 -> {
                                System.out.println("Logout successful!");
                                return;
                            }
                            default -> {
                                System.out.println("Invalid input!");
                                continue; // Go back to menu options
                            }
                        }

                        if (backToMain) {
                            break;
                        }
                    } while (true);
                }

                /* Admin Executive */
                case 3 -> {
                    LoginAdmin login = new LoginAdmin("adminE.txt");
                    login.LoginAdmin();
                    boolean backToMain = false;
                    do {
                        System.out.println("""
                           1. Resident/Tenant Management
                           2. Vendor Management
                           3. Complaint Management
                           4. Employee Management
                           5. Facility Management
                           6. Facility Booking Management
                           7. Logout
                           Select your choice
                           """);
                        int option = scan.nextInt();
                        switch (option) {
                            case 1 -> {
                                ResidentTenantManagement rtm = new ResidentTenantManagement();
                                rtm.runResident();
                            }
                            case 2 -> {
                                VendorManagement vm = new VendorManagement();
                                vm.runVendor();
                            }
                            case 3 -> {
                                ComplaintManagement complaint = new ComplaintManagement();
                                complaint.runComplaint();
                            }
                            case 4 -> {
                                EmployeeManagement employee = new EmployeeManagement();
                                employee.runEmployee();
                            }
                            case 5 -> {
                                FacilityManagement fm = new FacilityManagement();
                                fm.runFacility();
                            }
                            case 6 -> {
                                FacilityBooking fb = new FacilityBooking();
                                fb.runFacilityBooking();
                            }
                            case 7 -> {
                                System.out.println("Logout successful!");
                                backToMain = true;
                                break; // Return to user type selection
                            }
                            default -> {
                                System.out.println("Invalid input!");
                                continue; // Go back to menu options
                            }
                        }

                        if (backToMain) {
                            break;
                        }
                    } while (true);
                }


                /* Building Executive */
                case 4 -> {
                    LoginBE login = new LoginBE("buildingE.txt");
                    login.LoginBE();
                    boolean backToMain = false;

                    do {
                        System.out.println("""
                               1. Job management
                               2. View or update complaint status
                               3. Patrolling schedule and checkpoint
                               4. View patrol, complaint, job report
                               5. Logout
                               Select your choice:""");
                        int option = scan.nextInt();
                        switch (option) {
                            case 1 -> {
                                EmployeeTaskManager job = new EmployeeTaskManager();
                                job.Run();
                            }
                            case 2 -> {
                                ComplaintStatus cs = new ComplaintStatus();
                                cs.Complaint();
                            }
                            case 3 -> {
                                PatrollingandCheckpoint pc = new PatrollingandCheckpoint();
                                pc.start();
                            }
                            case 4 -> {
                                ViewBE view = new ViewBE();
                                view.showOptions();
                            }
                            case 5 -> {
                                System.out.println("Logout successful!");
                                return;
                            }
                            default -> {
                                System.out.println("Invalid input!");
                                continue; // Go back to menu options
                            }
                        }

                        if (backToMain) {
                            break;
                        }
                    } while (true);
                }


                /*Resident/Tenant */
                case 5 -> {
                    LoginRT Login = new LoginRT("residentfile.txt");
                    Login.loginRT();
                    boolean backToMain = false;

                    do {
                        System.out.println("""
                               1. View and update profile
                               2. Payment/Deposit
                               3. Check payment
                               4. Facility booking
                               5. Visitor pass
                               6. Complaint
                               7. Logout
                               Select your choice:""");
                        int option = scan.nextInt();
                        switch (option) {
                            /* profile */
                            case 1 -> {
                                ResidentProfile profile = new ResidentProfile();
                                profile.runProfile();
                            }
                            case 2 -> {
                                MakePaymentRT pay = new MakePaymentRT();
                                pay.MakePayment();

                            }
                            case 3 -> {
                                ViewRT view = new ViewRT();
                                view.ViewResidentTenant();
                            }
                            /* facility booking */
                            case 4 -> {
                                FacilityBookingRT booking = new FacilityBookingRT();
                                booking.runFacilityBookingRT();
                            }
                            /* visitor pass */
                            case 5 -> {
                                VisitorRT visitor = new VisitorRT();
                                visitor.runVisitorRT();
                            }
                            /* complaint */
                            case 6 -> {
                                ComplaintRT complaint = new ComplaintRT();
                                complaint.runComplaint();
                            }
                            case 7 -> {
                                System.out.println("Logout successful!");
                                return;
                            }
                            default -> {
                                System.out.println("Invalid input!");
                                continue; // Go back to menu options
                            }
                        }

                        if (backToMain) {
                            break;
                        }
                    } while (true);
                }

                /* vendor role */
                case 6 -> {
                    LoginVendor login = new LoginVendor("vendorfile.txt");
                    login.loginVendor();
                    boolean backToMain = false;

                    do {
                        System.out.println("""
                                        1. View and update profile
                                        2. Payment
                                        3. Check payment
                                        4. Complaint
                                        5. Log out
                                        Select your choice:
                                           """);
                        int option = scan.nextInt();
                        switch (option) {
                            case 1 -> {
                                VendorProfile profile = new VendorProfile();
                                profile.runProfile();
                            }
                            case 2 -> {
                                MakePaymentVendor pay = new MakePaymentVendor();
                                pay.MakePayment();
                            }
                            case 3 -> {
                                ViewVendor view = new ViewVendor();
                                view.viewPay();
                            }
                            case 4 -> {
                                ComplaintVendor complaint = new ComplaintVendor();
                                complaint.runComplaint();
                            }
                            case 5 -> {
                                System.out.println("Logout successful!");
                                return;
                            }
                            default -> {
                                System.out.println("Invalid input!");
                                continue; // Go back to menu options
                            }
                        }

                        if (backToMain) {
                            break;
                        }
                    } while (true);
                }


                /* Security Guard */
                case 7 -> {
                    LoginSG login = new LoginSG("employee.txt");
                    login.loginSG();
                    boolean backToMain = false;
                    do {
                        System.out.println("""
                               1. Search and view visitor pass
                               2. Update visitor entry
                               3. Checkpoint check-in
                               4. Record/update incident
                               5. Logout
                               Select your choice:""");
                        int option = scan.nextInt();
                        switch (option) {
                            case 1 -> {
                                SearchViewVisitor visitor = new SearchViewVisitor();
                                visitor.runViewVisitor();
                            }
                            case 2 -> {
                                RecordVisitor update = new RecordVisitor();
                                update.updateVisitor();
                            }
                            case 3 -> {
                                CheckIn check = new CheckIn();
                                check.CheckIn();
                            }
                            case 4 -> {
                                RecordIncident incident = new RecordIncident();
                                incident.run();
                            }
                            case 5 -> {
                                System.out.println("Logout successful!");
                                return;
                            }
                            default -> {
                                System.out.println("Invalid input!");
                                continue; // Go back to menu options
                            }
                        }

                        if (backToMain) {
                            break;
                        }
                    } while (true);
                }


                /* visitor */
                case 8 -> {
                    Visitor visitorPass = new Visitor();
                    visitorPass.runViewVisitor();
                }

                default ->
                    System.out.println("Invalid input!");
            }
            System.out.println("\nPlease Press 1 to Return to Back and Press 0 for End.");
            cont = scan.nextInt();

        } while (cont
                != 0);

    }
}
