package com.enrollment.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enrollment.model.Role;
import com.enrollment.model.User;
import com.enrollment.repository.RoleRepository;
import com.enrollment.repository.UserRepository;
import com.enrollment.service.UserService;
import com.enrollment.to.MessageResponse;
import com.enrollment.to.Signupdto;
@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@PersistenceContext
    private EntityManager manager;
	
	
	private Connection dbConnection;

	@Override
	public ResponseEntity<?> registeration(Signupdto signuodto) {
		// TODO Auto-generated method stub
		
		if(userRepo.existsByUsername(signuodto.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username is already exists!!"));
		}
		
		if(userRepo.existsByEmail(signuodto.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email is already exists"));
		}
		
		User users = new User();
		
		users.setFirstname(signuodto.getFirstname());
		users.setLastname(signuodto.getLastname());
		users.setUsername(signuodto.getUsername());
		users.setEmail(signuodto.getEmail());
		users.setPassword(passwordEncoder.encode(signuodto.getPassword()));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = null;
		try {
			birthDate = dateFormat.parse(signuodto.getDob());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		users.setDob(birthDate);
		users.setContact(signuodto.getContact());
		
		Role role = roleRepo.findByRole(signuodto.getRole());
		users.setRole(new Role(role.getId()));
		
		users.setCreatedon(new Date());
		
		userRepo.save(users);
		
		return ResponseEntity.ok(new MessageResponse("User Registered successfully!!"));
	}
	
	
	
	private Connection getConnection() {
		  
        try {
            if (dbConnection == null || dbConnection.isClosed()) {
                Session session = (Session) manager.getDelegate();
                session.doWork(new org.hibernate.jdbc.Work() {
                    @Override
                    public void execute(Connection connection) throws SQLException {
                        dbConnection = connection;
                    }
                });
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
}
	  
	
	public List<Signupdto> getUserDetails(String startDate,String endDate){
		
		List<Signupdto> userDetails = new ArrayList<Signupdto>();
		Signupdto userDetailsto;
		
		ResultSet resultSet = null;
		Connection oConn = null;
		PreparedStatement ps = null;
		
		try {
			
			oConn = getConnection();
			
			String query = null;
			
//			StringBuffer sbf = new StringBuffer("select o.username,o.email,o.dob,o.contact from user o"
//					+ " where o.createdon BETWEEN STR_TO_DATE('" +startDate+"','%Y-%m-%d') AND STR_TO_DATE('"+endDate+"','%Y-%m-%d') ");
			
			StringBuffer sbf = new StringBuffer("select o.username,o.email,o.dob,o.contact from user o"
					+ " where o.createdon>='"+startDate+"' and o.createdon<='"+endDate+"';");
			
			 query = sbf.toString();
			 ps = oConn.prepareStatement(query);
           
           resultSet = ps.executeQuery();
           
           while(resultSet.next()) {
        	   userDetailsto = new Signupdto();
        	   userDetailsto.setUsername(resultSet.getString(1));
        	   userDetailsto.setEmail(resultSet.getString(2));
        	   userDetailsto.setDob(resultSet.getString(3));
        	   userDetailsto.setContact(resultSet.getString(4));
        	   
        	   System.out.println(userDetailsto);
        	   userDetails.add(userDetailsto);
        	   
           }
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(userDetails);
		return userDetails;
	}
	

	@Override
	public void xlReportDetails(Signupdto signupdto,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String startDateforQuery = signupdto.getStartDate();
		String endDateforQuery = signupdto.getEndDate();
		
		List<Signupdto> signupTo = getUserDetails(startDateforQuery, endDateforQuery);
		
		try {
			
			XSSFWorkbook  workbook = new XSSFWorkbook();
         
			XSSFSheet sheet = workbook.createSheet("UserDetailsSheet");
			
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
     		font.setFontName("Arial");
     		style.setFont(font);
     		
     		sheet.setDefaultColumnWidth(20);
     		
     		int rowCount=1;
          
     		Row row = sheet.createRow(0);
      
          Cell cell = row.createCell(0);
          cell.setCellValue(" User Details are (" + signupdto.getStartDate() + "-" + signupdto.getStartDate() + ")");
         cell.setCellStyle(style);
          
          Row row1 = sheet.createRow(1);
          
          Cell cell0 = row1.createCell(0);         
          cell0.setCellValue("User Name");
          cell0.setCellStyle(style);
          
          Cell cell1 = row1.createCell(1);         
          cell1.setCellValue("Email");
          cell1.setCellStyle(style);
          
          Cell cell2 = row1.createCell(2);         
          cell2.setCellValue("Date Of Birth");
          cell2.setCellStyle(style);
          
          Cell cell3 = row1.createCell(3);         
          cell3.setCellValue("Contact");
          cell3.setCellStyle(style);
          
          
          for(Signupdto signup:signupTo) {
        	  
        	  rowCount++;
        	  row1 = sheet.createRow(rowCount);
        	  
        	  row1.createCell(0).setCellValue(signup.getUsername());
        	  row1.createCell(1).setCellValue(signup.getEmail());
        	  row1.createCell(2).setCellValue(signup.getDob());
        	  row1.createCell(3).setCellValue(signup.getContact());	  
        	  
          }
          
          
          try {
          
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
           workbook.write(outByteStream);
          byte[] outArray = outByteStream.toByteArray();
          response.setContentType("application/ms-excel");
          response.setContentLength(outArray.length);
          response.setHeader("Expires:", "0");
          response.setHeader("Content-Disposition",
                   "attachment; filename=VoucherTransactionReport.xlsx");
           OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
   catch(Exception e) {            
            e.printStackTrace();
    }	
	}

}
