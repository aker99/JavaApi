/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author himan
 */
final public class Validation {
    
   public static boolean chkPhoneNo(String phn){
       try{
            Long.parseLong(phn);
            if(phn.length()!=10){ 
                throw new NumberFormatException();
            }
        }              
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    public static boolean chkName(String name){
        for(char i=0;i<name.length();i++)
            if(!Character.isLetter(name.charAt(i)))
            {
                return false;
            }
            return true;
    }
  
    public static boolean chkEmail(String email){   
        boolean ch=false;
        char length = (char)email.length();
        
        for(char i=0;i<length;i++){
            if(email.charAt(i)=='@'&&i>0){
                i++;
                char j=i;
                for(;i<length;i++){
                    if(email.charAt(i)=='.'&&i>j&&i<length-1&&email.charAt(i-1)!='.'&&email.charAt(i+1)!='.')
                        ch=true;
                    else if(!Character.isLowerCase(email.charAt(i))){
                    return false;
                    }    
                }
            } 
            else if(!(Character.isDigit(email.charAt(i))||Character.isLowerCase(email.charAt(i))||email.charAt(i)=='.'||email.charAt(i)=='_')){
                return false;
            }
        }
        return ch;
   }
   
   
    public static boolean chkUser(String user){   
        char length = (char)user.length();
        System.out.println("len="+(int)length);
        if(length==0)
            return false;
        for(char i=0;i<length;i++){
            if(!(user.charAt(i)=='@'||user.charAt(i)=='_'||user.charAt(i)=='.'||(user.charAt(i)>='a'&&user.charAt(i)<='z')||(user.charAt(i)>='A'&&user.charAt(i)<='Z')||(user.charAt(i)>='0'&&user.charAt(i)<='9')))
                return false;
        }
        return true;
    }
     public static boolean chkPassword(char pass[]){
        boolean upper=false,lower=false,spcl=false,no=false;
        char length = (char)pass.length;
        if(length<8)
            return false;
        for(char i=0;i<length;i++){
            if(Character.isDigit(pass[i]))
                no=true;
            else if(Character.isLowerCase(pass[i]))
                lower=true;
            else if(Character.isUpperCase(pass[i]))
                upper=true;
            else 
                spcl=true;
            if(upper&&lower&&no&&spcl)
                return true;
        }
        return false;
    } 
     
}
