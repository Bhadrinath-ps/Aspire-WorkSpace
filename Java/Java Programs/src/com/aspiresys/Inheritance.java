package com.aspiresys;

//Parent class (Superclass)
class SupportEngineer {
 void troubleshoot() {
     System.out.println("Support Engineer is troubleshooting...");
 }

 void provideSupport() {
     System.out.println("Support Engineer is providing support...");
 }
}

//Single Inheritance: LeadSupportEngineer inherits from SupportEngineer
class LeadSupportEngineer extends SupportEngineer {
 void manageTeam() {
     System.out.println("Lead Support Engineer is managing the support team...");
 }
}

//Multilevel Inheritance: SeniorLeadSupportEngineer inherits from LeadSupportEngineer, which in turn inherits from SupportEngineer
class SeniorLeadSupportEngineer extends LeadSupportEngineer {
 void handleEscalations() {
     System.out.println("Senior Lead Support Engineer is handling escalations...");
 }
}

//Hierarchical Inheritance: Customer also inherits from SupportEngineer
class Customer extends SupportEngineer {
 void requestSupport() {
     System.out.println("Customer is requesting support...");
 }
}

public class Inheritance {
	
 public static void main(String[] args) {
     
     System.out.println("Single Inheritance:");
     System.out.println("-----------------------------");
     LeadSupportEngineer leadEngineer = new LeadSupportEngineer();
     leadEngineer.troubleshoot(); 
     leadEngineer.provideSupport(); 
     leadEngineer.manageTeam(); 
     
     System.out.println("\nMultilevel Inheritance:");
     System.out.println("-----------------------------");
     SeniorLeadSupportEngineer seniorLeadEngineer = new SeniorLeadSupportEngineer();
     seniorLeadEngineer.troubleshoot(); 
     seniorLeadEngineer.provideSupport();
     seniorLeadEngineer.manageTeam(); 
     seniorLeadEngineer.handleEscalations(); 
     
     System.out.println("\nHierarchical Inheritance:");
     System.out.println("-----------------------------");
     Customer customer = new Customer();
     customer.troubleshoot();  
     customer.provideSupport();
     customer.requestSupport();
     
 }
 
}
