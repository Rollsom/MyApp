package Creat;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Length = 10;
		int Range = 10;
		int j = 0; 
		String AnswersPath = null,ExercisePath = null;
		String Answers,Exercise;
		boolean flag = true;
		Saveanswer sa = new Saveanswer();
		for(int i = 0;i<args.length;i++)
		{
			if(args[i].equals("-n"))				
				Length =  Integer.valueOf(args[i+1]);							
			else if(args[i].equals("-r"))				
				Range =  Integer.valueOf(args[i+1]);										
			else if(args[i].equals("-a"))
			{
				AnswersPath = args[i+1];
				flag = false;
			}
			else if(args[i].equals("-e"))
				{
				ExercisePath = args[i+1];
				flag = false;
				}
			else if(args[i].matches("\\-\\w*")) {System.out.println("��������");return ;}
		}	
		if(flag==true)
		{
			ExpressionArray EA = new ExpressionArray(Length);		
			EA.CreatExpression(EA,Range);
			for(j = 0;j<EA.Expressioncount;j++)								//���ļ�д��ȥ
			{   
				
				Answers = String.valueOf(j+1)+". "+EA.e[j].Result.Fraction;
				Exercise = String.valueOf(j+1)+". "+EA.e[j].Expression+" = ";
				sa.save(Answers, "Answer.txt");
				sa.save(Exercise, "Exercises.txt");
				
			}
			System.out.println("�Ѿ������ϣ�");
			//sa.save("\n\n\n", "Answer.txt");
			//sa.save("\n\n\n", "Exercises.txt");
		}
		else {//�ȶ��ļ�
			sa.compare(ExercisePath, AnswersPath, "Grade.txt");
			
			
			}
	}
	
}
