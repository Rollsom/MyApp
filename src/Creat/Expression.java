package Creat;

 class Expression {
 char Operator ;//�������
 double value;//��ʽֵ
 boolean flag = false;//��ʽ���Ƿ������ţ�
 String Expression;//�������ʽ
 Data Operate1 ;
 Data Operate2 ;
 Data Result;
 String valueExpression;
 String SonExpression;
 Tree Node;

 boolean flag1;//�Ƿ���ʹ�á�
 public Expression() {
	  this.Operate1 = new Data();
	  this.Operate2 = new Data();
	  this.Result = new Data();
	   this.flag = false;
	   this.Operator = ' ';
	   this.Expression = "null";
	   this.flag1 = false;
	   this.Node = new Tree();
	   this.Node.e1 = new Tree();
	   this.Node.e2 = new Tree();
 }
 void GetExpression() {
	 String a = String.valueOf(this.Operator);
	 //String a1 = String.valueOf((int)this.Operate1);
	 //String a2 = String.valueOf((int)this.Operate2);
	 if(this.Operator==' ') this.Expression = this.Operate1.Fraction;
	 else if(this.flag == false)
	 this.Expression = this.Operate1.Fraction+ a +this.Operate2.Fraction;
	 else  this.Expression = "("+this.Operate1.Fraction+a+this.Operate2.Fraction+")";	 
	// System.out.println("���ʽ��"+this.Expression);
	 //System.out.println("���̴𰸣� "+this.Result.Fraction+"ֵΪ��"+this.Result.ExpressionValue);
	}
}

