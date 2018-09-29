package Creat;

import java.util.Random;

public class ExpressionArray {
int Expressioncount;
Expression[] e;
int MaxCount;

ExpressionArray(int count){
	this.Expressioncount = 0;
	this.e = new Expression[count];
	this.MaxCount = count;
}


 void StoreExpression(Expression ex) {
	this.e[this.Expressioncount++] = ex;
}
 
 boolean IFRepeat(Expression e) {
	 for(int i = 0;this.Expressioncount>i;i++)	 
		 if(IFequal(this.e[i].Node,e.Node)==true)  return true ; 	 
	 return false;
	 
 }
 
 boolean IFequal(Tree T1,Tree T2) {
	 	 if(T1==null&&T2==null) return true;
	 	 else if(T1==null||T2==null) return false;
	 else if(T1.value!=T2.value||T1.Operator!=T2.Operator) 
	 		 return false;
	 else  if((IFequal(T1.e1,T2.e1)&&IFequal(T1.e2,T2.e2))||(IFequal(T1.e2,T2.e1)&&IFequal(T1.e1,T2.e2)))
	 return true;
	 return false;
 }
 
 public  void CreatExpression(ExpressionArray EA,int Range) {
		Random r = new Random();
		int n = EA.MaxCount;
		while(n>0) {
			CQ cq = new CQ(Range);
		for(; cq.CurrentOperateNum<4;)
		{   
				Expression e = new Expression();	
				if(cq.CurrentOperateNum>=2&&r.nextInt(2)==1) break;
				cq.CQQ(e,cq.Range);//����һ���ӱ��ʽ
				cq.loadExpression(e);//���ӱ��ʽ����
				e.GetExpression();//����ӱ��ʽ
				}
				cq.GetBlankAdd(cq);//���û�и�ֵ��������
				cq.GroupExpression(cq,EA);//�����ɵ��ӱ��ʽ�齨����
				n--;
				//System.out.println("********************");	
				}		
		}
}
