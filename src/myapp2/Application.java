package myapp2;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.NodeFinder;

import bean.CauseABean;
import bean.CauseBBean;
import bean.FilePair;
import ca.mcgill.cs.swevo.ppa.PPAOptions;
import ca.mcgill.cs.swevo.ppa.ui.PPAUtil;
import db.DBUtils;
import myplugin.PPATypeVisitor;
import myplugin.PPATypeVisitorRaw;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {
	private static Logger log = Logger.getLogger(Application.class.getName());
	public static boolean hasValid = false;
	public static boolean hasError = false;

//	public Object start(IApplicationContext context) throws Exception {
//		File javaFile = new File("E:\\eclipse-workspace\\wsThesis\\com.sdh.downgit\\src\\main\\java\\chdistill\\source\\L.java");
//	    // CompilationUnit contains the AST of the partial program
//	    // PPAOptions is a wrapper and contains various configuration options:
//	    // most options are still not implemented, so the default options are
//	    // usually fine.
//	    CompilationUnit ast = PPAUtil.getCU(javaFile, new PPAOptions());
//	    PPATypeVisitorRaw visitor = new PPATypeVisitorRaw(System.out);
////	    NodeFinder finder = new NodeFinder(ast,27,89-27+1);
////	    NodeFinder finder = new NodeFinder(ast,123,133-123+1);
//	    NodeFinder finder = new NodeFinder(ast,138,148-138+1);
//		ASTNode coveredNode = finder.getCoveredNode();
//		System.out.println("~~coveredNode:"+coveredNode+"~~");
//		coveredNode.accept(visitor);
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		ASTNode parent = coveredNode.getParent();
////		System.out.println("~~parent:"+parent+"~~");
////	    parent.accept(visitor);
////	    System.out.println();
////		System.out.println();
////		System.out.println();
//	    System.out.println("whole!!!!!!!!!!!!!!!!!!!");
//	    ast.accept(visitor);
//	    System.out.println("end!!!!!!!!!!!!!!!!!!!");
//	    System.out.println(com.mysql.jdbc.Driver.class);
//	    System.out.println(DBUtils.hasInsertedCommit("asd"));
//		System.out.println(DBUtils.hasInsertedCommit("03bf2890109894f8725d962b8cc480ad48f2e3b3"));
//	    return IApplication.EXIT_OK;
//	}
//	public Object start(IApplicationContext context) throws Exception {
//		System.out.println(3);
//		System.out.println(com.mysql.jdbc.Driver.class);
//		System.out.println(DBUtils.hasInsertedCommit("sha"));
//		File javaFile = new File("E:\\方向论文\\隐性冲突\\gchq\\Gaffer\\16e93fbfae9c0261cf047af29ac55ad0424ba59f\\24GetWalksHandler.java\\right.java");
//		 PPAOptions option = new PPAOptions();
//		 option.setAllowMemberInference(true);
//		 option.setAllowCollectiveMode(true);
//		 option.setAllowTypeInferenceMode(true);
//		 option.setAllowMethodBindingMode(true);
//		CompilationUnit cu = PPAUtil.getCU(javaFile, option);
//		PPATypeVisitorRaw visitor = new PPATypeVisitorRaw(System.out);
//		cu.accept(visitor);
//		return IApplication.EXIT_OK;
//	}
	public Object start(IApplicationContext context) throws Exception {
		 System.out.println(3);
		// System.out.println(com.mysql.jdbc.Driver.class);
//		List<CauseBBean> causeBList = DBUtils.getAllCauseBs();
		List<CauseBBean> causeBList = DBUtils.getAllCauseBNullVaild();
		for (CauseBBean causeb : causeBList) {
			FilePair fp = DBUtils.getFilePair(causeb.filePairId);
			File rightFile = new File(fp.dir + "\\right");
			File rightjavaFile = new File(rightFile.getAbsolutePath() + ".java");
			if (!rightjavaFile.exists() && !rightFile.renameTo(rightjavaFile)) {
				System.err.println(rightFile + "rename fails");
			}
			PPAOptions option = new PPAOptions();
			option.setAllowMemberInference(true);
			option.setAllowCollectiveMode(true);
			option.setAllowTypeInferenceMode(true);
			option.setAllowMethodBindingMode(true);
			CompilationUnit ast = PPAUtil.getCU(rightjavaFile, option);
			CauseABean causeA = DBUtils.getCauseA(causeb.causeAId);
			PPATypeVisitor visitor = new PPATypeVisitor(System.out, causeb.id, causeA);
			hasValid = false;
			hasError = false;
			if (causeb.startPos >= 0) {
				NodeFinder finder = new NodeFinder(ast, causeb.startPos, causeb.endPos - causeb.startPos + 1);
				ASTNode coveredNode = finder.getCoveredNode();
				if (coveredNode != null)
					coveredNode.accept(visitor);
			} else {
				ast.accept(visitor);
			}
			DBUtils.updateCauseBValid(causeb.id, hasValid,hasError);
		}

		return IApplication.EXIT_OK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		// nothing to do
	}
}

// @Override
// public Object start(IApplicationContext context) throws Exception {
// System.out.println("Hello RCP World!");
// return IApplication.EXIT_OK;
// }
