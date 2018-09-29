package Creat;

import java.util.Random;

public class CQ {
	int MaxOperateNum = 4;
	int CurrentOperateNum = 0;
	int ExpressionNum = 0;//记录子表达式的个数
	Expression[] e  = new Expression[4]; 
	Expression FinalExpression ;
	Tree TreeNode;
	int Range;
	public CQ(int Range) {
		this.MaxOperateNum = 4;
		this.CurrentOperateNum = 0;
		this.ExpressionNum = 0;		
		this.TreeNode = new Tree();
		this.Range = Range;
	}
	
	public void CQQ(Expression e,int Range) {//创造表达式
		e.flag1 = true;
	Random r = new Random();
	if(this.CurrentOperateNum==3||this.CurrentOperateNum==0)
	  {	 
		e.Operate1.CreatNumble(Range);		
		 this.CurrentOperateNum += 1;
		 e.value = e.Operate1.ExpressionValue;		 
		 e.Result = e.Operate1;
		 this.ExpressionNum += 1;
		 e.flag1=true;
		 e.Node.value = e.Operate1.ExpressionValue;
		 }
	else if(this.CurrentOperateNum==4) return ;
	else {
		e.Operate1.CreatNumble(Range);
			this.ExpressionNum += 1;
			e.flag1 = true;
		if(r.nextInt(2)==1) //是否创造两个操作数
		{ 		   
			   e.flag = true;				   
			   e.Operate2.CreatNumble(Range); 				  
		   this.CurrentOperateNum += 2;
		   switch(r.nextInt(4)) 
		   {
		   case 0 : e.value = e.Operate1.ExpressionValue + e.Operate2.ExpressionValue; e.Operator = '+'; 		   			 
                 break;
		   case 1 : while(e.Operate1.ExpressionValue < e.Operate2.ExpressionValue) 
		   			{
			   		e.Operate1.CreatNumble(Range); 
			   		e.Operate2.CreatNumble(Range); 
			   		}
                 	e.value = e.Operate1.ExpressionValue - e.Operate2.ExpressionValue; e.Operator = '-';                
                 break;
		   case 2 : e.value = e.Operate1.ExpressionValue * e.Operate2.ExpressionValue; e.Operator = '*';
                 break;
		   case 3 : 
	             	e.value = e.Operate1.ExpressionValue / e.Operate2.ExpressionValue; e.Operator = '÷';
	             	break;	     
		   }
		   e.Result = e.Result.Carculate(e, e.Operate1, e.Operate2, e.Operator);
		   e.Node.e1.value = e.Operate1.ExpressionValue;
		   e.Node.e2.value = e.Operate2.ExpressionValue;
		   e.Node.value = e.value;
		   
			}
			else {						
				this.CurrentOperateNum += 1; 
				e.value = e.Operate1.ExpressionValue;
				e.Result = e.Operate1;
				e.Node.value = e.Operate1.ExpressionValue;
			 }				
			 }
}

	public void loadExpression(Expression e) //将生成的子表达式存起来
	{
		this.e[this.ExpressionNum-1] = e;
	}
	  
	public void GroupExpression(CQ cq,ExpressionArray EA) //组建父表达式与总表达式
	   {
		  Expression father1 = new Expression();
		  Expression father2 = new Expression();
		  cq.FinalExpression = new Expression();
		  father1.Expression = cq.GroupSonExpression(cq.e[0], cq.e[1], father1);//zujian
		 father2.Expression =  cq.GroupSonExpression(cq.e[2], cq.e[3], father2); 
		  cq.GroupFinalExpression(father1, father2, cq.FinalExpression);
		  if(EA.IFRepeat(cq.FinalExpression)==false)
		  {
			 // System.out.println(cq.FinalExpression.Expression+" = "+cq.FinalExpression.Result.Fraction);
			  EA.StoreExpression(cq.FinalExpression);
		  }		  		  
		  					  	   }
	   
	public void GetBlankAdd(CQ c) {//填充没有赋值的类数组
		for (int i=0;i<4;i++)
		{
			if(c.e[i]==null) 	
			{
				c.e[i] = new Expression();
				c.e[i].GetExpression();
			}			
		}				
	}
	
	public String GroupSonExpression(Expression e1,Expression e2,Expression father)
	{
		int Operate;
		Random r = new Random();
		Operate = r.nextInt(4);
		while((e1.value<e2.value&&Operate==1)||(e2.value==0&&Operate==3))
			Operate = r.nextInt(3);
		if(e1.flag1==true&&e2.flag1==true)
		{
			switch(Operate)
			{
			case 0 : 	father.Operator = '+';father.value = e1.value + e2.value;
						if(e1.flag==true)
							{
								e1.flag = false;
								e1.GetExpression();		   			
							}	   			
						if(e2.Operator=='*'||e2.Operator=='÷')
							{
							e2.flag = false;
							e2.GetExpression();
							}			
						break;
			case 1 :	father.Operator = '-';father.value = e1.value - e2.value;
						if(e1.flag==true)
						{
							e1.flag = false;   				
							e1.GetExpression();					
						}
						if(e2.Operator=='*'||e2.Operator=='÷')
						{
							e2.flag = false;
							e2.GetExpression();					
						}
						break;
			case 2 :	father.Operator = '*';father.value = e1.value * e2.value;
						if(e1.Operator=='*'||e1.Operator=='÷')
							{e1.flag = false;
							e1.GetExpression();}
							break;
			case 3 :father.Operator = '÷';father.value = e1.value / e2.value;
					if(e1.Operator=='*'||e1.Operator=='÷')
							{e1.flag = false;
							e1.GetExpression();}
					break;		
			}
			father.Result = father.Result.Carculate(father, e1.Result, e2.Result, father.Operator);			
			father.SonExpression = e1.Expression + father.Operator + e2.Expression;
			father.Expression =  "("+ father.SonExpression +")";
			father.flag1 = true;
			father.Node.value = father.value;
			father.Node.e1 = e1.Node;
			father.Node.e2 = e2.Node;
		}	
		else if(e2.flag1==false&&e1.flag1==true)		
			{
			father.value = e1.value;
			  father.Operate1 = e1.Operate1;
			  father.Operate2 = e1.Operate2;		  
			  father.Expression = e1.Operate1.Fraction;
			  father.Operator = e1.Operator;
			  father.Result = e1.Result;
			  father.flag = e1.flag;
			  father.Result.ExpressionValue = e1.Result.ExpressionValue;
			  father.flag1 = true;
			  if(e1.Operator==' ')
			  father.SonExpression = e1.Expression;
			  else 
				  father.SonExpression = e1.Operate1.Fraction+ father.Operator + e1.Operate2.Fraction;			  
			   father.Node = e1.Node;
			}
			else  father = e2;		
		return father.Expression;
			}
	
	public void GroupFinalExpression(Expression e1,Expression e2,Expression father) 
	   {
		String part1,part2;
		part1 = e1.Expression;
		part2 = e2.Expression;	
		if(e1.flag==true)
		part1 = "("+e1.SonExpression+")";
		if(e2.flag==true&&e2.Operator!=' ')
		part2 = "("+e2.SonExpression+")";
			Random r = new Random();
			 int Operator = r.nextInt(4);			
		   while((Operator==3&&e2.value==0)||(Operator==1&&e1.value<e2.value)) 
			   	Operator = r.nextInt(4);
		   if((e1.Operate1.ExpressionValue!=0||e1.Operator!=' ')&&(e2.Operate1.ExpressionValue!=0||e2.Operator!=' '))//两个操作数都不是默认操作数
		  {
			  switch(Operator) 
		   {
		   case 0 : father.value = e1.value + e2.value;father.Operator = '+'; 
		   			
		   				e1.flag = false;
		   					part1 = e1.SonExpression;	   					   				   			
		   			if(e2.Operator=='*'||e2.Operator=='÷')
		   			{
		   				e2.flag = false;
		   				part2 = e2.SonExpression;
		   			}
		   			break;
		   case 1 : father.value = e1.value - e2.value;father.Operator = '-'; 		   			
		   				e1.flag = false;
		   				part1 = e1.SonExpression;		   						   										   			
		   			if(e2.Operator=='*'||e2.Operator=='÷')
		   			{
		   				e2.flag = false;		   				
		   				part2 = e2.SonExpression;		   								
		   			}
		   				break;
		   case 2 : father.value = e1.value * e2.value;father.Operator = '*'; 
		   			if(e1.Operator=='*'||e1.Operator=='÷')
		   				{e1.flag = false;
		   				part1 = e1.SonExpression;
		   				}		   					   			
        			break;
		   case 3 : father.value = e1.value / e2.value;father.Operator = '÷'; 
		   			if(e1.Operator=='*'||e1.Operator=='÷')
		   				{e1.flag = false;
		   			part1 = e1.SonExpression;
		   				}		   					   				
        			break;
		   }
			  father.Result = father.Result.Carculate(father, e1.Result, e2.Result, father.Operator);
			  father.Operate1.ExpressionValue = e1.value;
			  father.Operate2.ExpressionValue = e2.value;		   
			  father.Expression = part1 + father.Operator + part2;
			  father.Node.value = father.value;
			  father.Node.e1 = e1.Node;
			  father.Node.e2 = e2.Node;			  
		  }
		  else if(e2.flag1==false&&e1.flag1==true){
			  father.value = e1.value;
			  father.Operate1 = e1.Operate1;
			  father.Operate2 = e1.Operate2;		  
			  father.Expression = e1.SonExpression;
			  father.Operator = e1.Operator;
			  father.Result = e1.Result;
			  father.flag = e1.flag;
			  father.Result.ExpressionValue = e1.Result.ExpressionValue;
			  father.Result.Fraction = e1.Result.Fraction;
			  father.Node.e1 = e1.Node.e1;
			  father.Node.e2 = e1.Node.e2;
			  father.Node.value = e1.value;
		  }
		  else  {
			  father = e2;
		  }
		  }		
}



