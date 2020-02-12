//package com.mrporter.pomangam.common.util.security;
//
//import com.mrporter.pomangam.humanResource.employee.domain.Employee;
//import com.mrporter.pomangam.common.security.user.domain.User;
//import org.springframework.security.core.Authentication;
//
//import javax.servlet.http.HttpSession;
//
//public class SessionConverter {
//    public static User getCustomer(HttpSession session) {
//        Object obj =  session.getAttribute("user");
//        if(obj != null && obj instanceof User) {
//            return (User) obj;
//        } else {
//            return null;
//        }
//    }
//
//    public static Employee getEmployee(HttpSession session) {
//        Object obj =  session.getAttribute("user");
//        if(obj != null && obj instanceof Employee) {
//            return (Employee) obj;
//        } else {
//            return null;
//        }
//    }
//
//    public static Authentication getAuthentication(HttpSession session) {
//        Object obj =  session.getAttribute("authentication");
//        if(obj != null && obj instanceof Authentication) {
//            return (Authentication) obj;
//        } else {
//            return null;
//        }
//    }
//
//}
