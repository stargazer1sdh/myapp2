package myplugin;

import java.io.PrintStream;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;

import bean.CauseABean1;
import myapp2.Application;


public class PPATypeVisitor1 extends ASTVisitor {

	private PrintStream printer;
	private int causeB1id;
	private CauseABean1 causeA1;
	
	public PPATypeVisitor1(PrintStream printer, int bid, CauseABean1 causeA) {
		super();
		this.printer = printer;
		causeB1id = bid;
		causeA1 = causeA;
	}

//	@Override
//	public boolean visit(FieldAccess node) {
//		boolean ans = super.visit(node);
//		System.out.println("FieldAccess Node:\t"+node);
//		System.out.println();
//		return ans;
//	}
//
//	@Override
//	public boolean visit(SuperFieldAccess node) {
//		boolean ans = super.visit(node);
//		System.out.println("SuperFieldAccess Node:\t"+node);
//		System.out.println();
//		return ans;
//	}
//
//	@Override
//	public boolean visit(ThisExpression node) {
//		boolean ans = super.visit(node);
//		System.out.println("ThisExpression Node:\t"+node);
//		System.out.println();
//		return ans;
//	}
//	
//	@Override
//	public boolean visit(QualifiedName node) {
//		boolean ans = super.visit(node);
//		System.out.println(" QualifiedName Node:\t"+node);
//		System.out.println();
//		return ans;
//	}

	@Override
	public boolean visit(SimpleName node) {
		boolean ans = super.visit(node);
		IBinding binding = node.resolveBinding();
		if (binding instanceof IVariableBinding) {
			IVariableBinding vBinding = (IVariableBinding) binding;
			if (vBinding.isField()) {
//				System.out.println("SimpleName Node:\t"+node);
//				System.out.println(binding);
				String decType = "nil";
				ITypeBinding declaringClass = vBinding.getDeclaringClass();
				if (declaringClass != null) {
					decType = declaringClass.getQualifiedName();//test.ppa.C
				}
				String fieldName = vBinding.getName();
//				System.out.println("DecType:"+decType+",\tfieldName:"+fieldName);
//				System.out.println();
				if (decType.equals(causeA1.className) && fieldName.equals(causeA1.fieldName)) {
					Application.hasValid = true;
				}
			}
		}
		return ans;
	}

}