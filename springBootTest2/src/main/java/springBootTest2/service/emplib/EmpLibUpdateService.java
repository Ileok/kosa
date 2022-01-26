package springBootTest2.service.emplib;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.EmpLibCommand;
import springBootTest2.domain.AuthInfo;
import springBootTest2.domain.EmpLibDTO;
import springBootTest2.mapper.EmpLibMapper;
import springBootTest2.mapper.EmployeeMapper;

@Component
@Service
public class EmpLibUpdateService {
   @Autowired
   EmpLibMapper emplibMapper;
   @Autowired
   EmployeeMapper empMapper;
   
   public String execute(EmpLibCommand emplibCommand, HttpSession session, Model model) {
      String path = "redirect:libInfo?num="+emplibCommand.getLibNum();
      AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
      String empId = authInfo.getUserId();
      Integer empNum = empMapper.getEmpNum(empId);

      
      EmpLibDTO dto = emplibMapper.selectOne(emplibCommand.getLibNum());
      if(!dto.getLibPw().equals(emplibCommand.getLibPw()) || 
            !dto.getEmpNum().equals(empNum)) {
         model.addAttribute("dto", dto);
         model.addAttribute("err_pw","비밀번호가 틀렸습니다.");
         path = "thymeleaf/emplib/empLibModify";
      }else {
         String [] storeFileNames = null;
         if( dto.getStoreFileName() != null) {
            storeFileNames = dto.getStoreFileName().split("`");
         }
         
         String fileDir = session.getServletContext().getRealPath("/view/empLib");
         
         dto.setLibContent(emplibCommand.getLibContent());
         dto.setLibNum(emplibCommand.getLibNum());
         dto.setLibSubject(emplibCommand.getLibSubject());
         dto.setLibWriter(emplibCommand.getLibWriter());
         
         String originalTotal = "";
         String storeTotal = "";
         String fileSizeTotal = "";
         for(MultipartFile mf : emplibCommand.getReport()) {
            String originalFile = mf.getOriginalFilename();
            String extension = originalFile.substring(
                  originalFile.lastIndexOf("."));
            String storeName = UUID.randomUUID().toString()
                              .replace("-", "");
            String storeFileName = storeName + extension;
            String fileSize = Long.toString(mf.getSize());
            
            File file = new File(fileDir + "/" + storeFileName);
            try{
               mf.transferTo(file);
            }catch(Exception e) {}
            originalTotal += originalFile + "`";
            storeTotal += storeFileName + "`";
            fileSizeTotal += fileSize +"`";
         }
         dto.setFileSize(fileSizeTotal);
         dto.setOriginalFileName(originalTotal);
         dto.setStoreFileName(storeTotal);
         
         Integer i = emplibMapper.emplibUpdate(dto);
         if(i >0 ) {
            for(String fileName : storeFileNames) {
               File file = new File(fileDir + "/" + fileName);
               if(file.exists())file.delete();
            }
         }
      }
      return path;
   }

   
}