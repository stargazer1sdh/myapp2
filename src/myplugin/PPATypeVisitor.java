package myplugin;

import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.PPABindingsUtil;

import bean.CauseABean;
import myapp2.Application;

public class PPATypeVisitor extends ASTVisitor {
	private static Logger log = Logger.getLogger(PPATypeVisitor.class.getName());

	private PrintStream printer;
	private int causeBid;
	private CauseABean causeA;
	
	public PPATypeVisitor(PrintStream printer, int causeBid, CauseABean causeA) {
		super();
		this.printer = printer;
		this.causeBid = causeBid;
		this.causeA = causeA;
	}

	public void postVisit (ASTNode node) {
		try {
			super.postVisit(node);
			if (node instanceof Expression) {
				Expression exp = (Expression) node;

				IBinding binding = null;
				if (exp instanceof Name) {
					Name name = (Name) exp;
					binding = name.resolveBinding();
				} else if (exp instanceof MethodInvocation) {
					MethodInvocation mi = (MethodInvocation) exp;
					binding = mi.resolveMethodBinding();
				} else if (exp instanceof ClassInstanceCreation) {
					ClassInstanceCreation cic = (ClassInstanceCreation) exp;
					binding = cic.resolveConstructorBinding();
				} else {
					return;
				}
				//			printer.println("Node: " + node.toString());
				//			ITypeBinding tBinding = exp.resolveTypeBinding();
				//			if (tBinding != null) {
				//				printer.println("  Type Binding: " + tBinding.getQualifiedName());
				//			}

				if (binding != null) {
					//				printer.println("  " + PPABindingsUtil.getBindingText(binding));
					if (binding instanceof IMethodBinding) {
						IMethodBinding methodBinding = (IMethodBinding) binding;
						String fullMethodSignature = PPABindingsUtil.getFullMethodSignature(methodBinding);//MBinding: boolean test.ppa.A:methodA()
						String className = PPABindingsUtil.getTypeString(methodBinding.getDeclaringClass());//test.ppa.A
						String methodName = fullMethodSignature.substring(1 + fullMethodSignature.indexOf(':'),
								fullMethodSignature.indexOf('('));//methodA
						if (className.equals(causeA.className) && methodName.equals(causeA.methodName)) {
							Application.hasValid = true;
						}
					} else if (binding instanceof IVariableBinding) {
						IVariableBinding vBinding = (IVariableBinding) binding;
						if (vBinding.isField()) { //FBinding: int test.ppa.C:field1
							String decType = "nil";
							ITypeBinding declaringClass = vBinding.getDeclaringClass();
							if (declaringClass != null) {
								decType = declaringClass.getQualifiedName();//test.ppa.C
							}
							String fieldName = vBinding.getName();//field1
							if (decType.equals(causeA.className) && fieldName.equals(causeA.fieldName)) {
								Application.hasValid = true;
							}
						}
					}
				}
				printer.flush();
			} 
		} catch (Exception e) {
			Application.hasError = true;
			System.err.println("postVisit error when causeBid:"+causeBid+"\t"+e);
			e.printStackTrace();
			log.error("postVisit error when causeBid:"+causeBid+"\t"+e);
		}
	}


}