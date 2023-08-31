//package com.emart.controller;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.emart.entities.Customer;
//import com.emart.exception.CustomerNotFoundException;
//import com.emart.services.CustomerManager;
//
///**
// * The CustomerController class handles the API endpoints related to Customer operations.
// */
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "https://react-app3-route-omkar-07-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com" , "https://react-app3-route-omkar-07-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
//public class CustomerController {
//
//    @Autowired
//    CustomerManager manager;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public static int  randomNumber;
//    /**
//     * Retrieves all the customers.
//     *
//     * @return ResponseEntity with the list of Customer objects if they exist,
//     *         or a no content response if no customers are found.
//     */
//    @GetMapping(value = "api/customers/")
//    public ResponseEntity<List<Customer>> showCustomers() {
//        List<Customer> customers = manager.getCustomers();
//        if (customers.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.ok(customers);
//        }
//    }
//    @GetMapping(value = "api/getbyemail/{email_id}")
//    public List<Customer> getCustomerbyemail(@PathVariable String email_id) {
//		System.out.println("inside cust by email");
//    	System.out.println(manager.getCustomerbyEmail(email_id));
//    	return manager.getCustomerbyEmail(email_id); 
//    }
//    
//    /**
//     * Retrieves a specific customer by their ID.
//     *
//     * @param customer_Id The ID of the customer to retrieve.
//     * @return ResponseEntity with the Customer if found,
//     *         or throws CustomerNotFoundException if the customer is not found.
//     */
//    @GetMapping(value = "api/customerById/{customer_Id}")
//    public ResponseEntity<Customer> getCustomer(@PathVariable int customer_Id) {
//        try {
//            Optional<Customer> customer = manager.getCustomer(customer_Id);
//            return customer.map(ResponseEntity::ok).orElseThrow(() ->
//                    new CustomerNotFoundException("Customer not found with ID: " + customer_Id));
//        } catch (CustomerNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//
//    /**
//     * Removes a customer by their ID.
//     *
//     * @param customer_Id The ID of the customer to remove.
//     * @return ResponseEntity with a success message if the customer is deleted successfully,
//     *         or an error message if the customer deletion fails.
//     */
//    @DeleteMapping(value = "api/customer/{customer_Id}")
//    public ResponseEntity<String> removeCustomer(@PathVariable int customer_Id) {
//        try {
//            manager.delete(customer_Id);
//            return ResponseEntity.ok("Customer deleted successfully.");
//        } catch (CustomerNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Customer not found with ID: " + customer_Id);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to delete customer: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Updates a customer's wallet balance by their ID.
//     *
//     * @param customer     The updated Customer object.
//     * @param customer_Id  The ID of the customer to update.
//     * @return ResponseEntity with a success message if the customer is updated successfully,
//     *         or an error message if the customer update fails.
//     */
//    @PutMapping(value = "api/customer/{customer_Id}")
//    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable int customer_Id) {
//        try {
//            manager.updateWallet(customer_Id, customer.getwallet());
//            return ResponseEntity.ok("Customer updated successfully.");
//        } catch (CustomerNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Customer not found with ID: " + customer_Id);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to update customer: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Adds a new customer.
//     *
//     * @param customer The Customer object to add.
//     * @return ResponseEntity with a success message if the customer is added successfully,
//     *         or an error message if the customer addition fails.
//     */
////    @PostMapping("/api/customer")
////	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
////		List<Customer> ls = (List<Customer>) showCustomers();
////		System.out.println(customer);
////		boolean flag = true;
////		try {
////			if (ls.size() == 0) {
////				System.out.println("0");
////				manager.addCustomer(customer);
////				Customer createdCustomer = manager.addCustomer(customer);
////				return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
////			} else {
////				System.out.println("inside for");
////				for (Customer customer2 : ls) {
////					if ((customer2.getemail_id().equals(customer.getemail_id())) || customer.getemail_id() == null) {
////						System.out.println("alerady exists");
////						flag = false;
////						System.out.println(customer2.getemail_id());
////						return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////					}
////				}
////				if (flag == true) {
////					System.out.println("added sucessfully");
////					Customer createdCustomer = manager.addCustomer(customer);
////					return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
////				}
////
////			}
////		} catch (Exception e) {
////			System.out.println("catch");
////			System.out.println(e.toString());
////			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////		}
////		System.out.println("outside");
////		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////	}
//    @PostMapping(value = "api/customer")
//    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
//       System.out.println("inside add customer");
//       boolean flag=false;
//    	try {
//    		 List<Customer> ls =manager.getCustomers();
//    	    	for (Customer c : ls) {	
//    	    		System.out.println(c.getemail_id());
//    	    		if(c.getemail_id().equals(customer.getemail_id())) {
//    	    			System.out.println("match");
//    	    			flag=true;
//    	    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email_id already exists");
//    	    		}
//    	    		
//    			}
//    	    	if(!flag) {
//    	    		ResponseEntity<String> s=sendEmail(customer.getemail_id());
//    	    		System.out.println(ResponseEntity.ok());
//    	    		System.out.println(s);
//    	    		int statusCode = s.getStatusCodeValue();
//    	    		System.out.println(statusCode);
//    	    		if(statusCode==200) {
//    	    			
//    	    		 manager.addCustomer(customer); 
//    	    		System.out.println("email send sucessfully");
//    	    		return ResponseEntity.ok("Customer added successfully.");
//    	    		}
//    	    		else {
//    	    			System.out.println("Inside else");
//    	    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not added");
//    	    		}
//      	    	}
//    	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not added");
//           
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to add customer: " + e.getMessage());
//        }
//    }
//    /**
//     * Retrieves a customer by their username.
//     *
//     * @param username The username of the customer to retrieve.
//     * @return ResponseEntity with the Customer if found,
//     *         or a no content response if the customer is not found.
//     */
////    @GetMapping(value = "api/getByUserName/{username}")
////    public ResponseEntity<Object> getCustomer(@PathVariable String username) {
////        try {
////            Optional<Object> customer = manager.getCustomer(username);
////            return customer.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
////        } catch (Exception e) {
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
////                    .body(null);
////        }
////    }
//    @PostMapping(value = "api/send-email")
//    public ResponseEntity<String> sendEmail(String email_id) {
//    	System.out.println("email");
//    	
//        try {
//            boolean shouldSendEmail = true; // Add your condition here to determine when to send the email
//             randomNumber = generateRandomNumber();
//            if (shouldSendEmail) {
//                SimpleMailMessage message = new SimpleMailMessage();
//                message.setTo(email_id);
//                message.setSubject("onestop Email");
//                System.out.println(randomNumber);
//                message.setText("This is a registration email sent from Spring Boot.");
//                message.setText("OTP is - "+randomNumber);
//                mailSender.send(message);
//                System.out.println("send sucessfully");
//                return ResponseEntity.ok("Email sent successfully.");
//            } else {
//            	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                         .body("Failed to send email:");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to send email: " + e.getMessage());
//        }
//    }
//    public static int generateRandomNumber() {
//        Random random = new Random();
//        int min = 1000; // Minimum 6-digit number
//        int max = 9999; // Maximum 6-digit number
//        return random.nextInt(max - min + 1) + min;
//    }
//    @PostMapping("api/validateopt")
//    public static ResponseEntity<Boolean> validateOtp(@RequestBody String number) {
//    	
//    	System.out.println(number);
//    	System.out.println(randomNumber);
//        if (number.equals(randomNumber)) {
//        	System.out.println("if");
//            return ResponseEntity.ok(true); // Number is valid, return HTTP 200 OK with "true" body
//        } else {
//        	System.out.println("else");
//        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(false);
//       // Number is invalid, return HTTP 200 OK with "false" body
//        }
//    }
//
//    
//    }
//    





package com.emart.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emart.entities.Customer;
import com.emart.exception.CustomerNotFoundException;
import com.emart.service.MailService;
import com.emart.services.CustomerManager;

/**
 * The CustomerController class handles the API endpoints related to Customer operations.
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://react-app3-route-omkar-07-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com" , "https://react-app3-route-omkar-07-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
public class CustomerController {

    @Autowired
    CustomerManager manager;

    @Autowired
    private JavaMailSender mailSender;

    public static int randomNumber;
    
    @Autowired
    public  MailService mailService ;
    /**
     * Retrieves all the customers.
     *
     * @return ResponseEntity with the list of Customer objects if they exist,
     *         or a no content response if no customers are found.
     */
    @GetMapping(value = "api/customers/")
    public ResponseEntity<List<Customer>> showCustomers() {
        List<Customer> customers = manager.getCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(customers);
        }
    }
    
    @GetMapping(value = "api/getbyemail/{email_id}")
    public List<Customer> getCustomerByEmail(@PathVariable String email_id) {
		System.out.println("inside cust by email");
    	System.out.println(manager.getCustomerbyEmail(email_id));
    	return manager.getCustomerbyEmail(email_id); 
    }
    
    /**
     * Retrieves a specific customer by their ID.
     *
     * @param customer_Id The ID of the customer to retrieve.
     * @return ResponseEntity with the Customer if found,
     *         or throws CustomerNotFoundException if the customer is not found.
     */
    @GetMapping(value = "api/customerById/{customer_Id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customer_Id) {
        try {
            Optional<Customer> customer = manager.getCustomer(customer_Id);
            return customer.map(ResponseEntity::ok).orElseThrow(() ->
                    new CustomerNotFoundException("Customer not found with ID: " + customer_Id));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Removes a customer by their ID.
     *
     * @param customer_Id The ID of the customer to remove.
     * @return ResponseEntity with a success message if the customer is deleted successfully,
     *         or an error message if the customer deletion fails.
     */
    @DeleteMapping(value = "api/customer/{customer_Id}")
    public ResponseEntity<String> removeCustomer(@PathVariable int customer_Id) {
        try {
            manager.delete(customer_Id);
            return ResponseEntity.ok("Customer deleted successfully.");
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer not found with ID: " + customer_Id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete customer: " + e.getMessage());
        }
    }

    /**
     * Updates a customer's wallet balance by their ID.
     *
     * @param customer     The updated Customer object.
     * @param customer_Id  The ID of the customer to update.
     * @return ResponseEntity with a success message if the customer is updated successfully,
     *         or an error message if the customer update fails.
     */
    @PutMapping(value = "api/customer/{customer_Id}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable int customer_Id) {
        try {
            manager.updateWallet(customer_Id, customer.getwallet());
            return ResponseEntity.ok("Customer updated successfully.");
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer not found with ID: " + customer_Id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update customer: " + e.getMessage());
        }
    }

    /**
     * Adds a new customer.
     *
     * @param customer The Customer object to add.
     * @return ResponseEntity with a success message if the customer is added successfully,
     *         or an error message if the customer addition fails.
     */
    @PostMapping(value = "api/customer/{email}")
    public ResponseEntity<String> addCustomer(@PathVariable String email) {
        System.out.println("inside add customer");
       System.out.println(email);
        try {
            boolean shouldSendEmail = true; // Add your condition here to determine when to send the email
             randomNumber = generateRandomNumber();
            
            if (shouldSendEmail) {
                ResponseEntity<String> emailResponse = sendEmail(email);
                if (emailResponse.getStatusCodeValue() == 200) {
                System.out.println(randomNumber);

                    System.out.println("Email send successfully"+randomNumber);
                    return ResponseEntity.ok("Email send successfully."+randomNumber);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
                }
            } else {
//                manager.addCustomer(customer);
//                System.out.println("added successfully");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add customer: " + e.getMessage());
        }
    }
    
    /**
     * Sends an email with the OTP to the specified email address.
     *
     * @param email The email address to send the OTP to.
     * @return ResponseEntity with a success message if the email is sent successfully,
     *         or an error message if the email sending fails.
     * @throws IOException 
     */
//    @PostMapping(value = "api/send-email")
//    public ResponseEntity<String> sendEmail(String email) {
//    	System.out.println("email");
//    	System.out.println(email);
//        try {
//            boolean shouldSendEmail = true; // Add your condition here to determine when to send the email
//             randomNumber = generateRandomNumber();
//            
//            if (shouldSendEmail) {
//                SimpleMailMessage message = new SimpleMailMessage();
//                message.setTo(email);
//                message.setSubject("onestop Email");
//                message.setText("This is a registration email sent from Spring Boot.");
//                message.setText("OTP is - " + randomNumber);
//                mailSender.send(message);
//                System.out.println("send successfully");
//                return ResponseEntity.ok("Email sent successfully.");
//            } else {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body("Failed to s"end email: ");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to send email: " + e.getMessage());
//        }
//    }
    
    
    
    @PostMapping("/api/send-email")
    public ResponseEntity<String> sendEmail( String email) throws IOException {
        try {
            boolean shouldSendEmail = true; // Add your condition here to determine when to send the email
            randomNumber = generateRandomNumber();
            if (shouldSendEmail) {
                String to = email;
                String subject = "Verify the OTP .....!!!";
                String templateFileName = "email.html";
                String htmlContent = readHtmlFromTemplate(templateFileName);
                htmlContent = htmlContent.replace("{{otp}}",String.valueOf(randomNumber));
//                String htmlContent = "<!doctype html>\r\n"
//                		+ "<html>\r\n"
//                		+ "  <head>\r\n"
//                		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n"
//                		+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n"
//                		+ "    <title>Registration Mail from onestop</title>\r\n"
//                		+ "    <style>\r\n"
//                		+ "      /* -------------------------------------\r\n"
//                		+ "          GLOBAL RESETS\r\n"
//                		+ "      ------------------------------------- */\r\n"
//                		+ "      \r\n"
//                		+ "      /*All the styling goes here*/\r\n"
//                		+ "      \r\n"
//                		+ "      img {\r\n"
//                		+ "        border: none;\r\n"
//                		+ "        -ms-interpolation-mode: bicubic;\r\n"
//                		+ "        max-width: 100%; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      body {\r\n"
//                		+ "        background-color: #f6f6f6;\r\n"
//                		+ "        font-family: sans-serif;\r\n"
//                		+ "        -webkit-font-smoothing: antialiased;\r\n"
//                		+ "        font-size: 14px;\r\n"
//                		+ "        line-height: 1.4;\r\n"
//                		+ "        margin: 0;\r\n"
//                		+ "        padding: 0;\r\n"
//                		+ "        -ms-text-size-adjust: 100%;\r\n"
//                		+ "        -webkit-text-size-adjust: 100%; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      table {\r\n"
//                		+ "        border-collapse: separate;\r\n"
//                		+ "        mso-table-lspace: 0pt;\r\n"
//                		+ "        mso-table-rspace: 0pt;\r\n"
//                		+ "        width: 100%; }\r\n"
//                		+ "        table td {\r\n"
//                		+ "          font-family: sans-serif;\r\n"
//                		+ "          font-size: 14px;\r\n"
//                		+ "          vertical-align: top; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* -------------------------------------\r\n"
//                		+ "          BODY & CONTAINER\r\n"
//                		+ "      ------------------------------------- */\r\n"
//                		+ "\r\n"
//                		+ "      .body {\r\n"
//                		+ "        background-color: #f6f6f6;\r\n"
//                		+ "        width: 100%; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\r\n"
//                		+ "      .container {\r\n"
//                		+ "        display: block;\r\n"
//                		+ "        margin: 0 auto !important;\r\n"
//                		+ "        /* makes it centered */\r\n"
//                		+ "        max-width: 580px;\r\n"
//                		+ "        padding: 10px;\r\n"
//                		+ "        width: 580px; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* This should also be a block element, so that it will fill 100% of the .container */\r\n"
//                		+ "      .content {\r\n"
//                		+ "        box-sizing: border-box;\r\n"
//                		+ "        display: block;\r\n"
//                		+ "        margin: 0 auto;\r\n"
//                		+ "        max-width: 580px;\r\n"
//                		+ "        padding: 10px; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* -------------------------------------\r\n"
//                		+ "          HEADER, FOOTER, MAIN\r\n"
//                		+ "      ------------------------------------- */\r\n"
//                		+ "      .main {\r\n"
//                		+ "        background: #ffffff;\r\n"
//                		+ "        border-radius: 3px;\r\n"
//                		+ "        width: 100%; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .wrapper {\r\n"
//                		+ "        box-sizing: border-box;\r\n"
//                		+ "        padding: 20px; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .content-block {\r\n"
//                		+ "        padding-bottom: 10px;\r\n"
//                		+ "        padding-top: 10px;\r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .footer {\r\n"
//                		+ "        clear: both;\r\n"
//                		+ "        margin-top: 10px;\r\n"
//                		+ "        text-align: center;\r\n"
//                		+ "        width: 100%; \r\n"
//                		+ "      }\r\n"
//                		+ "        .footer td,\r\n"
//                		+ "        .footer p,\r\n"
//                		+ "        .footer span,\r\n"
//                		+ "        .footer a {\r\n"
//                		+ "          color: #999999;\r\n"
//                		+ "          font-size: 12px;\r\n"
//                		+ "          text-align: center; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* -------------------------------------\r\n"
//                		+ "          TYPOGRAPHY\r\n"
//                		+ "      ------------------------------------- */\r\n"
//                		+ "      h1,\r\n"
//                		+ "      h2,\r\n"
//                		+ "      h3,\r\n"
//                		+ "      h4 {\r\n"
//                		+ "        color: #000000;\r\n"
//                		+ "        font-family: sans-serif;\r\n"
//                		+ "        font-weight: 400;\r\n"
//                		+ "        line-height: 1.4;\r\n"
//                		+ "        margin: 0;\r\n"
//                		+ "        margin-bottom: 30px; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      h1 {\r\n"
//                		+ "        font-size: 35px;\r\n"
//                		+ "        font-weight: 300;\r\n"
//                		+ "        text-align: center;\r\n"
//                		+ "        text-transform: capitalize; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      p,\r\n"
//                		+ "      ul,\r\n"
//                		+ "      ol {\r\n"
//                		+ "        font-family: sans-serif;\r\n"
//                		+ "        font-size: 14px;\r\n"
//                		+ "        font-weight: normal;\r\n"
//                		+ "        margin: 0;\r\n"
//                		+ "        margin-bottom: 15px; \r\n"
//                		+ "      }\r\n"
//                		+ "        p li,\r\n"
//                		+ "        ul li,\r\n"
//                		+ "        ol li {\r\n"
//                		+ "          list-style-position: inside;\r\n"
//                		+ "          margin-left: 5px; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      a {\r\n"
//                		+ "        color: #3498db;\r\n"
//                		+ "        text-decoration: underline; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* -------------------------------------\r\n"
//                		+ "          BUTTONS\r\n"
//                		+ "      ------------------------------------- */\r\n"
//                		+ "      .btn {\r\n"
//                		+ "        box-sizing: border-box;\r\n"
//                		+ "        width: 100%; }\r\n"
//                		+ "        .btn > tbody > tr > td {\r\n"
//                		+ "          padding-bottom: 15px; }\r\n"
//                		+ "        .btn table {\r\n"
//                		+ "          width: auto; \r\n"
//                		+ "      }\r\n"
//                		+ "        .btn table td {\r\n"
//                		+ "          background-color: #ffffff;\r\n"
//                		+ "          border-radius: 5px;\r\n"
//                		+ "          text-align: center; \r\n"
//                		+ "      }\r\n"
//                		+ "        .btn a {\r\n"
//                		+ "          background-color: #ffffff;\r\n"
//                		+ "          border: solid 1px #3498db;\r\n"
//                		+ "          border-radius: 5px;\r\n"
//                		+ "          box-sizing: border-box;\r\n"
//                		+ "          color: #3498db;\r\n"
//                		+ "          cursor: pointer;\r\n"
//                		+ "          display: inline-block;\r\n"
//                		+ "          font-size: 14px;\r\n"
//                		+ "          font-weight: bold;\r\n"
//                		+ "          margin: 0;\r\n"
//                		+ "          padding: 12px 25px;\r\n"
//                		+ "          text-decoration: none;\r\n"
//                		+ "          text-transform: capitalize; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .btn-primary table td {\r\n"
//                		+ "        background-color: #3498db; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .btn-primary a {\r\n"
//                		+ "        background-color: #3498db;\r\n"
//                		+ "        border-color: #3498db;\r\n"
//                		+ "        color: #ffffff; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* -------------------------------------\r\n"
//                		+ "          OTHER STYLES THAT MIGHT BE USEFUL\r\n"
//                		+ "      ------------------------------------- */\r\n"
//                		+ "      .last {\r\n"
//                		+ "        margin-bottom: 0; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .first {\r\n"
//                		+ "        margin-top: 0; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .align-center {\r\n"
//                		+ "        text-align: center; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .align-right {\r\n"
//                		+ "        text-align: right; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .align-left {\r\n"
//                		+ "        text-align: left; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .clear {\r\n"
//                		+ "        clear: both; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .mt0 {\r\n"
//                		+ "        margin-top: 0; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .mb0 {\r\n"
//                		+ "        margin-bottom: 0; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .preheader {\r\n"
//                		+ "        color: transparent;\r\n"
//                		+ "        display: none;\r\n"
//                		+ "        height: 0;\r\n"
//                		+ "        max-height: 0;\r\n"
//                		+ "        max-width: 0;\r\n"
//                		+ "        opacity: 0;\r\n"
//                		+ "        overflow: hidden;\r\n"
//                		+ "        mso-hide: all;\r\n"
//                		+ "        visibility: hidden;\r\n"
//                		+ "        width: 0; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      .powered-by a {\r\n"
//                		+ "        text-decoration: none; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      hr {\r\n"
//                		+ "        border: 0;\r\n"
//                		+ "        border-bottom: 1px solid #f6f6f6;\r\n"
//                		+ "        margin: 20px 0; \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* -------------------------------------\r\n"
//                		+ "          RESPONSIVE AND MOBILE FRIENDLY STYLES\r\n"
//                		+ "      ------------------------------------- */\r\n"
//                		+ "      @media only screen and (max-width: 620px) {\r\n"
//                		+ "        table.body h1 {\r\n"
//                		+ "          font-size: 28px !important;\r\n"
//                		+ "          margin-bottom: 10px !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        table.body p,\r\n"
//                		+ "        table.body ul,\r\n"
//                		+ "        table.body ol,\r\n"
//                		+ "        table.body td,\r\n"
//                		+ "        table.body span,\r\n"
//                		+ "        table.body a {\r\n"
//                		+ "          font-size: 16px !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        table.body .wrapper,\r\n"
//                		+ "        table.body .article {\r\n"
//                		+ "          padding: 10px !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        table.body .content {\r\n"
//                		+ "          padding: 0 !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        table.body .container {\r\n"
//                		+ "          padding: 0 !important;\r\n"
//                		+ "          width: 100% !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        table.body .main {\r\n"
//                		+ "          border-left-width: 0 !important;\r\n"
//                		+ "          border-radius: 0 !important;\r\n"
//                		+ "          border-right-width: 0 !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        table.body .btn table {\r\n"
//                		+ "          width: 100% !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        table.body .btn a {\r\n"
//                		+ "          width: 100% !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        table.body .img-responsive {\r\n"
//                		+ "          height: auto !important;\r\n"
//                		+ "          max-width: 100% !important;\r\n"
//                		+ "          width: auto !important; \r\n"
//                		+ "        }\r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "      /* -------------------------------------\r\n"
//                		+ "          PRESERVE THESE STYLES IN THE HEAD\r\n"
//                		+ "      ------------------------------------- */\r\n"
//                		+ "      @media all {\r\n"
//                		+ "        .ExternalClass {\r\n"
//                		+ "          width: 100%; \r\n"
//                		+ "        }\r\n"
//                		+ "        .ExternalClass,\r\n"
//                		+ "        .ExternalClass p,\r\n"
//                		+ "        .ExternalClass span,\r\n"
//                		+ "        .ExternalClass font,\r\n"
//                		+ "        .ExternalClass td,\r\n"
//                		+ "        .ExternalClass div {\r\n"
//                		+ "          line-height: 100%; \r\n"
//                		+ "        }\r\n"
//                		+ "        .apple-link a {\r\n"
//                		+ "          color: inherit !important;\r\n"
//                		+ "          font-family: inherit !important;\r\n"
//                		+ "          font-size: inherit !important;\r\n"
//                		+ "          font-weight: inherit !important;\r\n"
//                		+ "          line-height: inherit !important;\r\n"
//                		+ "          text-decoration: none !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        #MessageViewBody a {\r\n"
//                		+ "          color: inherit;\r\n"
//                		+ "          text-decoration: none;\r\n"
//                		+ "          font-size: inherit;\r\n"
//                		+ "          font-family: inherit;\r\n"
//                		+ "          font-weight: inherit;\r\n"
//                		+ "          line-height: inherit;\r\n"
//                		+ "        }\r\n"
//                		+ "        .btn-primary table td:hover {\r\n"
//                		+ "          background-color: #34495e !important; \r\n"
//                		+ "        }\r\n"
//                		+ "        .btn-primary a:hover {\r\n"
//                		+ "          background-color: #34495e !important;\r\n"
//                		+ "          border-color: #34495e !important; \r\n"
//                		+ "        } \r\n"
//                		+ "      }\r\n"
//                		+ "\r\n"
//                		+ "    </style>\r\n"
//                		+ "  </head>\r\n"
//                		+ "  <body>\r\n"
//                		+ "    <span class=\"preheader\">This is otp verifucation mail.</span>\r\n"
//                		+ "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\r\n"
//                		+ "      <tr>\r\n"
//                		+ "        <td>&nbsp;</td>\r\n"
//                		+ "        <td class=\"container\">\r\n"
//                		+ "          <div class=\"content\">\r\n"
//                		+ "\r\n"
//                		+ "            <!-- START CENTERED WHITE CONTAINER -->\r\n"
//                		+ "            <table role=\"presentation\" class=\"main\">\r\n"
//                		+ "\r\n"
//                		+ "              <!-- START MAIN CONTENT AREA -->\r\n"
//                		+ "              <tr>\r\n"
//                		+ "                <td class=\"wrapper\">\r\n"
//                		+ "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
//                		+ "                    <tr>\r\n"
//                		+ "                      <td>\r\n"
//                		+ "                        <p><b>Dear User</b></p>\r\n"
//                		+ "                        <p>Welcome to onestop.This is the registraion mail from onestop.</p>\r\n"
//                        + "<p>Your One-Time Password (OTP) is: <strong>" + randomNumber + "</strong></p>"
//                		+ "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n"
//                		+ "                          <tbody>\r\n"
//                		+ "                            <tr>\r\n"
//                		+ "                              <td align=\"left\">\r\n"
//                		+ "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
//                		+ "                                  <tbody>\r\n"
//                		+ "                                    <tr>\r\n"
//                		+ "                                      <td> <a href=\"http://localhost:3000/Verify\" target=\"_blank\">verify otp</a> </td>\r\n"
//                		+ "                                    </tr>\r\n"
//                		+ "                                  </tbody>\r\n"
//                		+ "                                </table>\r\n"
//                		+ "                              </td>\r\n"
//                		+ "                            </tr>\r\n"
//                		+ "                          </tbody>\r\n"
//                		+ "                        </table>\r\n"
//                		+ "                        <p>Click the button with no distractions to verify the otp.</p>\r\n"
//                		+ "                        <p>Good luck! Hope it works.</p>\r\n"
//                		+ "                      </td>\r\n"
//                		+ "                    </tr>\r\n"
//                		+ "                  </table>\r\n"
//                		+ "                </td>\r\n"
//                		+ "              </tr>\r\n"
//                		+ "\r\n"
//                		+ "            <!-- END MAIN CONTENT AREA -->\r\n"
//                		+ "            </table>\r\n"
//                		+ "            <!-- END CENTERED WHITE CONTAINER -->\r\n"
//                		+ "\r\n"
//                		+ "            <!-- START FOOTER -->\r\n"
//                		+ "            <div class=\"footer\">\r\n"
//                		+ "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
//                		+ "                <tr>\r\n"
//                		+ "                  <td class=\"content-block\">\r\n"
//                		+ "                    <span class=\"apple-link\">Infobellit,Bangalore 94102</span>\r\n"
////                		+ "                    <br> Don't like these emails? <a href=\"http://i.imgur.com/CScmqnj.gif\">Unsubscribe</a>.\r\n"
//                		+ "                  </td>\r\n"
//                		+ "                </tr>\r\n"
//                		+ "                <tr>\r\n"
//                		+ "                  <td class=\"content-block powered-by\">\r\n"
//                		+ "                    Powered by <a href=\"http://localhost:3000\">HTMLemail</a>.\r\n"
//                		+ "                  </td>\r\n"
//                		+ "                </tr>\r\n"
//                		+ "              </table>\r\n"
//                		+ "            </div>\r\n"
//                		+ "            <!-- END FOOTER -->\r\n"
//                		+ "\r\n"
//                		+ "          </div>\r\n"
//                		+ "        </td>\r\n"
//                		+ "        <td>&nbsp;</td>\r\n"
//                		+ "      </tr>\r\n"
//                		+ "    </table>\r\n"
//                		+ "  </body>\r\n"
//                		+ "</html>";

                mailService.sendEmail(to, subject, htmlContent);

                return ResponseEntity.ok("Email sent successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to send email: Your condition to send email is not met.");
            }
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
    private String readHtmlFromTemplate(String templateFileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/" + templateFileName);
        byte[] contentBytes = Files.readAllBytes(Paths.get(resource.getURI()));
        return new String(contentBytes, StandardCharsets.UTF_8);
    }
    /**
     * Validates the provided OTP.
     *
     * @param randomNumber2 The OTP to validate.
     * @return ResponseEntity with a boolean indicating if the OTP is valid or not.
     */
    @PostMapping("api/addcustme")
    public ResponseEntity<String> validateOtp(@RequestBody Customer customer) {
    
    	System.out.println(randomNumber);
     System.out.println(customer);
    	
        if (customer!=null) {
        	manager.addCustomer(customer);
            return ResponseEntity.ok("Customer added successfully"); 
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failded to add customer");
        } 
        }

    
    /**
     * Generates a random 4-digit number.
     *
     * @return The generated random number.
     */
    public static int generateRandomNumber() {
        Random random = new Random();
        int min = 1000; // Minimum 4-digit number
        int max = 9999; // Maximum 4-digit number
        return random.nextInt(max - min + 1) + min;
    }
}
