package cn.maxlu.demo.springmvc.controller.fileupload;

import cn.maxlu.demo.springmvc.common.util.FileUtils;
import cn.maxlu.demo.springmvc.controller.common.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @RequestMapping("/1")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse upload1(@RequestPart("uploadFile") byte[] uploadFile, HttpServletRequest request) {
        String servletPath = request.getServletContext().getRealPath("/");
        File file = Paths.get(servletPath, "upload", "storage", "tmp.txt").toFile();
        if (!FileUtils.createFile(file)) {
            return RestResponse.fail("create file fail");
        }

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(uploadFile);
            outputStream.flush();
        } catch (IOException e) {
            return RestResponse.fail("fail when write file");
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        return RestResponse.success("upload file success");
    }



    @RequestMapping("/2")
    @ResponseStatus(HttpStatus.CREATED)
    public Object upload2(@RequestPart("uploadFile") MultipartFile uploadFile, HttpServletRequest request) {
        String servletPath = request.getServletContext().getRealPath("/");
        File file = Paths.get(servletPath, "upload", "storage", uploadFile.getOriginalFilename()).toFile();
        if (!FileUtils.createFile(file)) {
            return RestResponse.fail("create file fail");
        }
        try {
            uploadFile.transferTo(file);
        } catch (IOException e) {
            return RestResponse.fail("fail when write file");
        }

        return RestResponse.success("upload file success");
    }

    /**
     * TODO 还是不行啊，受不了类
     */
    @RequestMapping("/3")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse upload3(@RequestPart(value = "uploadFile", required = false) Part uploadFile, HttpServletRequest request) {
        String servletPath = request.getServletContext().getRealPath("/");
        String path = Paths.get(servletPath, "upload", "storage", uploadFile.getName()).toString();
        try {
            uploadFile.write(path);
        } catch (IOException e) {
            return RestResponse.fail("fail when write file");
        }
        return RestResponse.success("upload file success");
    }

    @RequestMapping("/4")
    public RestResponse testError() {
        String a = null;
        a.toString();
        return null;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse exceptionHandler(Throwable e){
        e.printStackTrace();
        return RestResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

}
