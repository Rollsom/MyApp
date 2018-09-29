package Creat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Saveanswer {
	void save(String answer,String answerfilename) {
		try {
			FileWriter fw =new FileWriter(answerfilename,true);
			BufferedWriter bfw=new BufferedWriter(fw);
			if(answer!=null) {			
			bfw.write(answer);
			bfw.newLine();
			bfw.flush();
			fw.flush();
			}
			fw.close();
			bfw.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
	
	
	
	
	void compare(String answer,String reply,String count) {
		try {
			FileReader fra=new FileReader(answer);
			FileReader frr=new FileReader(reply);
			FileWriter frc=new FileWriter(count);
			BufferedReader bfra=new BufferedReader(fra);
			BufferedReader bfrr=new BufferedReader(frr);
			BufferedWriter bfrc=new BufferedWriter(frc);
			
			
			
			int correctnumber = 0;
			int wrongnumber = 0;
			String correct ="Correct: ";
			String wrong="Wrong: ";
			String correctcount = "";
			String wrongcount ="";
			String tempa;
			String tempr;
			
			int questionnumber= 0;
			
			
			while((tempa=bfra.readLine())!=null&&(tempr=bfrr.readLine())!=null) {
				questionnumber++;
			if(tempa.equals(tempr)) {
				if(correctnumber<9) correctcount=correctcount+" "+String.valueOf(questionnumber)+" "+",";
				else if(correctnumber!=0&&correctnumber%20==0)				
					correctcount=correctcount+String.valueOf(questionnumber)+","+"\r\n                    ";									
				else correctcount=correctcount+String.valueOf(questionnumber)+",";
				correctnumber++;
			}else {
				if(wrongnumber<9) wrongcount=wrongcount+" "+String.valueOf(questionnumber)+" "+",";
				else if(wrongnumber!=0&&wrongnumber%20==0)
					wrongcount=wrongcount+String.valueOf(questionnumber)+","+"\r\n                    ";
				else wrongcount=wrongcount+String.valueOf(questionnumber)+",";
				wrongnumber++;
			}	
			}
			
			
			correct=correct+String.valueOf(correctnumber)+"("+correctcount+")";
			wrong=wrong+String.valueOf(wrongnumber)+"("+wrongcount+")";
			correct=correct.replaceAll("\\,\\)", ")");
			wrong=wrong.replaceAll("\\,\\)", ")");
			bfrc.write(correct);
			bfrc.newLine();
			bfrc.write(wrong);
			bfrc.flush();
			
			
			frc.close();
			fra.close();
			frr.close();
			bfra.close();
			bfrr.close();
			bfrc.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}

