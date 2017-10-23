import javax.swing.JTextField;
public class Compiler {
    public String code="";
    public String satırlar[];
    public static final int kodSatırı=100;
    String Kod[][] = new String[kodSatırı][];
    String etiketler[] = new String[kodSatırı];
    public static int satır=0,sıra=0;
    Compiler(){
        Ketum.zero.setText("0");Ketum.Mem0.setText("0");
        Ketum.a0.setText("0");Ketum.Mem1.setText("0");
        Ketum.v0.setText("0");Ketum.Mem2.setText("0");
        Ketum.t0.setText("0");Ketum.Mem3.setText("0");
        Ketum.t1.setText("0");Ketum.Mem4.setText("0");
        Ketum.s0.setText("0");Ketum.Mem5.setText("0");
        Ketum.s1.setText("0");Ketum.Mem6.setText("0");
        Ketum.ra.setText("0");Ketum.Message.setText(null);
    }
    Compiler(String kod){
        code=kod;
        Trim();
        satırlar=code.split("\n");
        int r = 0;
        for (String row : satırlar)
        {
            Kod[r++] = row.split(" ");
        }
        for(int i=0;i<satırlar.length;i++)
        {
            etiketler[i]=Kod[i][0];
        }
    }
    private int s(){
        if(Kod[satır].length==5)
        return 2;
        else
        return 1; 
    }
    public final void Trim(){
        code=code.replaceAll(",", "");
        code=code.replaceAll("\t", " ");
        while(code.contains("(")||code.contains(")"))
        {
            code=code.replace("($"," $");
            code=code.replace(")", "");
        }
        while(code.contains("  ") || code.contains("\n ") || code.contains("\n\n"))
        {
            code=code.replaceAll("  ", " ");
            code=code.replaceAll("\n ", "\n");
            code=code.replaceAll("\n\n", "\n");
        }
        code=code.trim();
    }
    public void step(){
        Ketum.Message.setText(Integer.toString(satır+1));
        switch(Kod[satır][sıra])
        {
            case "add":
                add();
                a();
                break;
            case "sub":
                sub();
                a();
                break;
            case "and":
                and();
                a();
                break;
            case "or":
                or();
                a();
                break;
            case "sll":
                sll();
                a();
                break;
            case "srl":
                srl();
                a();
                break;
            case "addi":
                addi();
                a();
                break;
            case "andi":
                andi();
                a();
                break;
            case "lw":
                lw();
                a();
                break;
            case "sw":
                sw();
                a();
                break;
            case "beq":
                beq();
                break;
            case "bne":
                bne();
                break;
            case "j":
                j();
                break;
            case "jr":
                jr();
                break;
            case "jal":
                jal();
                break;
            case "add.s":
                adds();
                break;
            default:
                sıra=1;
                break;
        } 
    }
    public void a(){
        if(satır<satırlar.length-1)
        {
            satır++;
            sıra=0;
        }
    }
    public JTextField Mem(int z){
        switch(z)
        {
            case 0:
            case 1:
                return Ketum.Mem0;
            case 2:
            case 3:
                return Ketum.Mem1;
            case 4:
            case 5:
                return Ketum.Mem2;
            case 6:
            case 7:
                return Ketum.Mem3;
            case 8:
            case 9:
                return Ketum.Mem4;
            case 10:
            case 11:
                return Ketum.Mem5;
            case 12:
            case 13:
                return Ketum.Mem6;
            default:
                return Ketum.Mem0;
        }
    }
    public JTextField varRec(int sıra){
        switch(Kod[satır][sıra])
        {
            case "$zero":
                return Ketum.zero;
            case "$a0":
                return Ketum.a0;
            case "$v0":
                return Ketum.v0;
            case "$t0":
                return Ketum.t0;
            case "$t1":
                return Ketum.t1;
            case "$s0":
                return Ketum.s0;
            case "$s1":
                return Ketum.s1;
            case "$ra":
                return Ketum.ra;
            default:
                Ketum.constant.setText(Kod[satır][sıra]);
                return Ketum.constant;
        }
                
    }
    public void add(){
        short a=(short) (Integer.parseInt(varRec(s()+1).getText())
                                                     +Integer.parseInt(varRec(s()+2).getText()));
        varRec(s()).setText(Integer.toString(a));
    }
    public void sub(){
        short a=(short) (Integer.parseInt(varRec(s()+1).getText())
                                                     -Integer.parseInt(varRec(s()+2).getText()));
         varRec(s()).setText(Integer.toString(a));
     }
    public void and(){
        if(varRec(s()+1).getText().equals(varRec(s()+2).getText()) && varRec(s()+1).getText().equals("1"))
            varRec(s()).setText("1");
        else
            varRec(s()).setText("0");
     }
    public void or(){
        if(varRec(s()+1).getText().equals(varRec(s()+2).getText()) && varRec(s()+1).getText().equals("0"))
            varRec(s()).setText("0");
        else
            varRec(s()).setText("1");
     }
    public void sll(){
        int a=(int)Math.pow(2, Integer.parseInt(varRec(s()+2).getText()));
        int b=Integer.parseInt(varRec(s()+1).getText());
        short c=(short) (b*a);
        varRec(s()).setText(Integer.toString(c));
         
     }
    public void srl(){
        int a=(int)Math.pow(2, Integer.parseInt(varRec(s()+2).getText()));
        int b=Integer.parseInt(varRec(s()+1).getText());
        short c;
        Ketum.Message.setText(Integer.toString(a));
        if(a>=b)
            c=0;
        else
            c=(short) (b/a);
        varRec(s()).setText(Integer.toString(c));
    }
    public void addi(){
        short a=(short) (Integer.parseInt(varRec(s()+1).getText())
                                                     +Integer.parseInt(varRec(s()+2).getText()));
        varRec(s()).setText(Integer.toString(a));
     }
    public void andi(){
        if(varRec(s()+1).getText().equals(varRec(s()+2).getText()) && varRec(s()+1).getText().equals("1"))
            varRec(s()).setText("1");
        else
            varRec(s()).setText("0");
     }
    public void lw(){
        short b=(short) (Integer.parseInt(varRec(s()+2).getText())+(Integer.parseInt(varRec(s()+1).getText())));
        varRec(s()).setText(Mem(b).getText());         
     }
    public void sw(){
        short b=(short) (Integer.parseInt(varRec(s()+2).getText())+(Integer.parseInt(varRec(s()+1).getText())));
        Mem(b).setText(varRec(s()).getText());
     }
    public void beq(){
         if(varRec(s()).getText().equals(varRec(s()+1).getText()))
         {
             for(int i=0; i<etiketler.length; i++)
             if(varRec(s()+2).getText().equals(etiketler[i]))
                satır=i;
         }
         else
         {
             a();
         }
     }
    public void bne(){
         if(varRec(s()).getText().equals(varRec(s()+1).getText()))
         {
             a();
         }
         else
         {
             for(int i=0; i<etiketler.length; i++)
             if(varRec(s()+2).getText().equals(etiketler[i]))
                satır=i;
         }
     }
    public void j(){
         for(int i=0; i<etiketler.length; i++)
             if(varRec(s()).getText().equals(etiketler[i]))
                satır=i;
     }
    public void jal(){
         Ketum.ra.setText(Integer.toString(satır+1));
         for(int i=0; i<etiketler.length; i++)
             if(varRec(s()).getText().equals(etiketler[i]))
                satır=i;
     }
    public void jr(){
         satır=Integer.parseInt(Ketum.ra.getText());
     }
    public void adds(){
        float c=(Float.parseFloat(varRec(s()+1).getText())
                                                     +Float.parseFloat(varRec(s()+2).getText()));
        
        varRec(s()).setText(Float.toString(c%65536));
         
     }
}