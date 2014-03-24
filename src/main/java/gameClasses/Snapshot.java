package gameClasses;

public class Snapshot{
	private Field[][] field;
	char color;
	char next;
	int fieldSize;
	public Snapshot(Field[][] data,char col, int fieldSize,char next){
		int count1,count2;
		this.next=next;
		this.fieldSize=fieldSize;
		field  = new Field[fieldSize][fieldSize];
		color = col;
		for(count1=0;count1<fieldSize;count1++)
			for(count2=0;count2<fieldSize;count2++)
				field[count1][count2]=data[count1][count2];
	}
	
	@Override
	public String toString(){
		StringBuilder resp=new StringBuilder();
		resp.append("{\"status\":\"snapshot\",");
		resp.append("\"next\":\""+next+"\",");
		if(color=='w')
			resp.append("\"color\":\"w\",");
		else
			resp.append("\"color\":\"b\",");
		resp.append("\"field\":");
		int count1, count2;
		resp.append("[");
		for(count1=0;count1<fieldSize;count1++){
			if(count1!=0)
				resp.append(", ");
			resp.append("[");
			for(count2=0;count2<fieldSize;count2++){
				if(count2!=0)
					resp.append(", ");
				resp.append("\""+field[count1][count2].getType()+"\"");
			}
			resp.append("]");
		}
		resp.append("]");
		resp.append(",\"king\":");
		resp.append("[");
		for(count1=0;count1<fieldSize;count1++){
			if(count1!=0)
				resp.append(", ");
			resp.append("[");
			for(count2=0;count2<fieldSize;count2++){
				if(count2!=0)
					resp.append(", ");
				resp.append("\""+field[count1][count2].isKing()+"\"");
			}
			resp.append("]");
		}
		resp.append("]");
		resp.append("}");
		return resp.toString();
	}

	public String toStringTest(){
		StringBuilder resp=new StringBuilder();
		resp.append("{'status':'snapshot',");
		resp.append("'next':'"+next+"',");
		if(color=='w')
			resp.append("'color':'w',");
		else
			resp.append("'color':'b',");
		resp.append("'field':");
		int count1, count2;
		resp.append("[");
		for(count1=0;count1<fieldSize;count1++){
			if(count1!=0)
				resp.append(", ");
			resp.append("[");
			for(count2=0;count2<fieldSize;count2++){
				if(count2!=0)
					resp.append(", ");
				resp.append("'"+field[count1][count2].getType()+"'");
			}
			resp.append("]");
		}
		resp.append("]");
		resp.append(",'king':");
		resp.append("[");
		for(count1=0;count1<fieldSize;count1++){
			if(count1!=0)
				resp.append(", ");
			resp.append("[");
			for(count2=0;count2<fieldSize;count2++){
				if(count2!=0)
					resp.append(", ");
				resp.append("'"+field[count1][count2].isKing()+"'");
			}
			resp.append("]");
		}
		resp.append("]");
		resp.append("}");
		return resp.toString();
	}
}