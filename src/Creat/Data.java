package Creat;

import java.util.Random;

public class Data {
int Molecule;//分子
int Deno;//分母
int integer;//整数
String Fraction;//表达式
double ExpressionValue;//操作数的实际值
int flags;
Data(){
	this.Deno = 1;
	this.Molecule = 0;
	this.integer = 0;
	this.ExpressionValue = 0;
	this.flags = 0;
}
public int  GetMaxCommonFactor(int num1, int num2) {//化简分数		
	int n1,n2;
	n1 = num1;
	n2 = num2;
	int MaxCommonFactor  = 	n1;
	if(n1 < n2) {MaxCommonFactor = n1;n1 = n2;n2 = MaxCommonFactor;}
	if(n2==0) return n1;
	else n1 = GetMaxCommonFactor(n2 , n1 % n2);
	 return n1;
}

public int GetMinCommonFactor(int num1,int num2) {
	int Min;
	Min = num1 * num2/GetMaxCommonFactor(num1, num2);
	return Min;
}

public void Simplication() {
	int i;
	i = GetMaxCommonFactor(this.Molecule, this.Deno);
	this.Molecule = this.Molecule/i;
	this.Deno = this.Deno/i;
	//System.out.println("最大公因数为："+this.Molecule+"%"+this.Deno+" = "+i);
	while(this.Molecule>=this.Deno) {	
		this.integer++;
this.Molecule = this.Molecule-this.Deno;
	}
	
	if(this.Molecule==0)this.flags=0;
	else if(this.integer==0) this.flags = 1;
	else this.flags = 2;
}

public void  CreatNumble(int Range) {
	Random r = new Random();
	if(r.nextInt(2)==1)
		{
		this.integer = r.nextInt(Range-1)+1;
		this.flags = 0;
		}
	else {
		this.Molecule = r.nextInt(Range-1)+1;
		this.Deno = r.nextInt(Range-2)+2;
		this.Simplication();
		if(this.Molecule==0)this.flags=0;
		else if(this.integer==0) this.flags = 1;
		else this.flags = 2;
	}	
	this.ExpressionValue = (double)this.integer+(double)(this.Molecule)/(double)this.Deno;
	this.CreatFractor();
}

public void CreatFractor() {
	if(this.flags==0)
		this.Fraction = String.valueOf(this.integer);
	else if(this.flags==1)
		this.Fraction = String.valueOf(this.Molecule)+"/"+String.valueOf(this.Deno);
	else this.Fraction = String.valueOf(this.integer)+"'"+String.valueOf(this.Molecule)+"/"+String.valueOf(this.Deno);
	
}

public Data Carculate(Expression e,Data d1,Data d2,char Operator) {
	Data d = new Data();
	switch(Operator)
	{
	case '+':d.Molecule = d1.Molecule * d2.Deno + d2.Molecule * d1.Deno;
			 d.Deno = d1.Deno * d2.Deno;
			 d.integer = d1.integer + d2.integer;			 
			 d.Simplication();
			 d.ExpressionValue = (double)d.integer+(double)(d.Molecule)/(double)d.Deno;
			 d.CreatFractor();
			 e.valueExpression = d.Fraction;
			 break;
	case '-':
			d.Molecule = d2.Deno * (d1.integer * d1.Deno+d1.Molecule) - d1.Deno * (d2.integer * d2.Deno + d2.Molecule);
			//d1.Molecule * d2.Deno - d2.Molecule * d1.Deno;
	 		 d.Deno = d1.Deno * d2.Deno;
	 		 d.integer = 0;	 		 
	 		 d.Simplication();
	 		 d.ExpressionValue = (double)d.integer+(double)(d.Molecule)/(double)d.Deno;
	 		 d.CreatFractor();
	 		 e.valueExpression = d.Fraction;
	 		 break;
	case '*':
			 d.Molecule = (d1.Molecule+d1.integer * d1.Deno) * (d2.Molecule + d2.integer * d2.Deno);
			 d.Deno = d1.Deno * d2.Deno;
			 d.integer = 0;
			 d.Simplication();
			 d.ExpressionValue = (double)d.integer+(double)(d.Molecule)/(double)d.Deno;
			 d.CreatFractor();
			 e.valueExpression = d.Fraction;
			 break;
	case '÷':d.Molecule = (d1.Molecule+d1.integer * d1.Deno) * d2.Deno;
			 d.Deno = d1.Deno * (d2.Molecule + d2.integer * d2.Deno);	
			 d.integer = 0;
			 d.Simplication();
			 d.ExpressionValue = (double)d.integer+(double)(d.Molecule)/(double)d.Deno;
			 d.CreatFractor();
			 e.valueExpression = d.Fraction;
			 break;
			 default : System.out.println("计算出错");break;
	}	
	return d;
}
}
