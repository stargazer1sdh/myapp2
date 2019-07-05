package myapp2;

import java.io.File;
import java.util.List;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.NodeFinder;

import bean.CauseABean1;
import bean.CauseBBean1;
import bean.FilePair;
import ca.mcgill.cs.swevo.ppa.PPAOptions;
import ca.mcgill.cs.swevo.ppa.ui.PPAUtil;
import db.DBUtils;
import myplugin.PPATypeVisitor1;

/**
 * This class controls all aspects of the application's execution
 */
public class Application1 implements IApplication {
	public static boolean hasValid = false;
	public static boolean hasError = false;
	private File lastrightFile = null;
	private CompilationUnit ast = null;

	public Object start(IApplicationContext context) throws Exception {
		System.out.println(com.mysql.jdbc.Driver.class);
//		List<CauseBBean1> causeB1List = DBUtils.getAllCauseB1s();
		List<CauseBBean1> causeB1List = DBUtils.getAllCauseB1sNullVaild();
		for (CauseBBean1 causeb1 : causeB1List) {
			FilePair fp = DBUtils.getFilePair(causeb1.filePairId);
			File rightFile = new File(fp.dir + "\\right.java");
			if(!rightFile.equals(lastrightFile)) {
				lastrightFile = rightFile;
				PPAOptions option = new PPAOptions();
				option.setAllowMemberInference(true);
				option.setAllowCollectiveMode(true);
				option.setAllowTypeInferenceMode(true);
				option.setAllowMethodBindingMode(true);
				ast = PPAUtil.getCU(rightFile, option);
			}
			CauseABean1 causeA = DBUtils.getCauseA1(causeb1.causeAId);
			PPATypeVisitor1 visitor = new PPATypeVisitor1(System.out, causeb1.id, causeA);
			hasValid = false;
			hasError = false;
			if (causeb1.startPos >= 0) {
				NodeFinder finder = new NodeFinder(ast, causeb1.startPos, causeb1.endPos - causeb1.startPos + 1);
				ASTNode coveredNode = finder.getCoveredNode();
				if (coveredNode != null)
					coveredNode.accept(visitor);
			} else {
				ast.accept(visitor);
			}
			DBUtils.updateCauseB1Valid(causeb1.id, hasValid,hasError);
		}

		return IApplication.EXIT_OK;
	}
//	public Object start(IApplicationContext context) throws Exception {
////		System.out.println(3);
////		System.out.println(com.mysql.jdbc.Driver.class);
////		System.out.println(DBUtils.hasInsertedCommit("sha"));
////		File javaFile = new File("E://eclipse-workspace//wsThesis//com.sdh.downgit//src//main//java//chdistill//source//R.java");
//		File javaFile = new File("E:\\方向论文\\ewRcpOxygen\\myapp2\\src\\source\\L.java");
//		 PPAOptions option = new PPAOptions();
//		 option.setAllowMemberInference(true);
//		 option.setAllowCollectiveMode(true);
//		 option.setAllowTypeInferenceMode(true);
//		 option.setAllowMethodBindingMode(true);
//		CompilationUnit ast = PPAUtil.getCU(javaFile, option);
//		PPATypeVisitor1 visitor = new PPATypeVisitor1(System.out, 0, null);
//		ast.accept(visitor);
//		return IApplication.EXIT_OK;
//	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		// nothing to do
	}
}
