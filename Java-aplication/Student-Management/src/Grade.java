
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class Grade {
    private Student std;
    private Subject sbj;
    private double lab, Ptest, FE;
    private String status;

    public Grade() {
        this.std = null;
        this.sbj = null;
        this.lab = 0;
        this.Ptest = 0;
        this.FE = 0;
    }

    public Grade(Student std, Subject sbj, double lab, double Ptest, double FE) {
        this.std = std;
        this.sbj = sbj;
        this.lab = lab;
        this.Ptest = Ptest;
        this.FE = FE;
    }

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }

    public Subject getSbj() {
        return sbj;
    }

    public void setSbj(Subject sbj) {
        this.sbj = sbj;
    }

    public double getLab() {
        return lab;
    }

    public void setLab(double lab) {
        this.lab = lab;
    }

    public double getPtest() {
        return Ptest;
    }

    public void setPtest(double Ptest) {
        this.Ptest = Ptest;
    }

    public double getFE() {
        return FE;
    }

    public void setFE(double FE) {
        this.FE = FE;
    }
    
    public double calculateAverage()
    {
        return (this.lab*30/100 + this.Ptest*30/100 + this.FE*40/100);
    }

    public String getStatus() {
        if(calculateAverage()<4.0) this.status = "Not pass";
        else this.status = "Pass";
        return status;
    }
    
    public static Comparator objCompare = new Comparator<Grade>() {
        @Override
        public int compare(Grade s1, Grade s2) {
           if(s1.getSbj().getSubjName().compareTo(s2.getSbj().getSubjName()) > 0)
               return 1;
           else{
               if(s1.getSbj().getSubjName().compareTo(s2.getSbj().getSubjName()) < 0)
                   return -1;
               else{
                   return 0;
                   }
               }
           }
        };
    
    public static Comparator objCompareStd = new Comparator<Grade>() {
        @Override
        public int compare(Grade s1, Grade s2) {
           if(s1.getStd().getLastName().compareTo(s2.getStd().getLastName()) > 0)
               return 1;
           else{
               if(s1.getStd().getLastName().compareTo(s2.getStd().getLastName()) < 0)
                   return -1;
               else{
                   return 0;
                   }
               }
           }
        };

}
