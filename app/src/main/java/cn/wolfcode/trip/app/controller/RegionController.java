package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.service.IRegionService;
import com.github.pagehelper.PageInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private IRegionService regionService;


    @GetMapping
    public List<Region> listAll(Integer state){
        return regionService.listAll(state);
    }


}
