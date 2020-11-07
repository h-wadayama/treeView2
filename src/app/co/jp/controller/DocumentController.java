package app.co.jp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.co.jp.business.IF.RegisterTreeViewIF;
import app.co.jp.model.DocModel;
import app.co.jp.model.DocumentModel;
import app.co.jp.service.IF.DocServiceIF;
import app.co.jp.service.IF.DocumentDownLoadServiceIF;
import app.co.jp.service.IF.DocumentServiceIF;
import app.co.jp.service.IF.DocumentUploadServiceIF;
import app.co.jp.util.Const;
import app.co.jp.util.PropertiesUtil;

@Controller
public class DocumentController {
    private static final Logger logger = LoggerFactory
            .getLogger(DocumentController.class);

    @Autowired
    RegisterTreeViewIF register;

	@Autowired
	DocServiceIF docService;

	@Autowired
	DocumentServiceIF documentService;

	@Autowired
	DocumentUploadServiceIF documentUploadService;

	@Autowired
	DocumentDownLoadServiceIF documentDownLoadService;

	@Autowired
	PropertiesUtil propertiesUtil;

	/*
	 * 処理名     ：階層表示
	 * メソッド名 ：document
	 *
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2020-04-23
	 */
	@RequestMapping(value = "/dtree" ,method = RequestMethod.GET)
	public String document(Model model){

		try {
//			List<DocModel> docModel = new ArrayList<DocModel>();
			List<DocumentModel> documentModel = new ArrayList<DocumentModel>();
//			docModel.addAll(docService.getTree());
			documentModel.addAll(documentService.getAllHierarchy());

			model.addAttribute("documentModel",documentModel);

		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return "Document";

	}

	/*
	 * 処理名     ：階層表示
	 * メソッド名 ：document
	 *
	 * 作成者     ： H.Wadayama
	 * 作成日     ：2020-04-23
	 */
	@RequestMapping(value = "/jsTree" ,method = RequestMethod.GET)
	public String document2(Model model){


		try {
			List<DocModel> docModel = new ArrayList<DocModel>();
			docModel.addAll(docService.getTree());

			//ModelをJson形式に置き換える。
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(docModel);

			String projectName =  propertiesUtil.getText(Const.PROJECT);
			model.addAttribute("project", projectName);
			model.addAttribute("jsonData",jsonData);
			//model.addAttribute("jsonData","test");

		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return "Document2";

	}

	@RequestMapping(value = "/jsTree/Edit" ,produces="text/plan;charset=UTF-8",method = RequestMethod.POST)
	public String Edit(
			HttpServletRequest request,
			@RequestParam MultipartFile fileUpload,
			@RequestParam("element") String element,
			@RequestParam("jsonData") String jsonData,
			Model model) throws ParseException{

		String id = "";
		String icon = "";
		if (!element.isEmpty()){
			String[] elm = element.split(",");
			id = elm[0];
			icon = elm[1];
			icon=icon.trim();
		}
		jsonData = register.registerInTree(fileUpload, jsonData, id,icon);

		model.addAttribute("jsonData",jsonData);
		return "Document2";

	}

	@RequestMapping(value = "/jsTree/Download" ,produces="text/plan;charset=UTF-8",method = RequestMethod.GET)
	public void Download(
			@RequestParam(value = "Id" ,defaultValue = "0") String Id,
			HttpServletResponse response,
			Model model){

		try {
			documentDownLoadService.downLoad(Id, response);

			System.out.print("OK");

		} catch (NamingException | SQLException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}



}
