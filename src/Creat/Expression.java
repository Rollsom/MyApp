package Creat;

 class Expression {
 char Operator ;//运算符号
 double value;//算式值
 boolean flag = false;//算式内是否有括号？
 String Expression;//算术表达式
 Data Operate1 ;
 Data Operate2 ;
 Data Result;
 String valueExpression;
 String SonExpression;
 Tree Node;

 boolean flag1;//是否有使用。
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
	// System.out.println("表达式："+this.Expression);
	 //System.out.println("过程答案： "+this.Result.Fraction+"值为："+this.Result.ExpressionValue);
	}
}

