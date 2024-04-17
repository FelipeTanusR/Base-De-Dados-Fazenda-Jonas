/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fazenda;

import java.util.Comparator;

/**
 *
 * @author felipe
 */
public class MeuComparador implements Comparator {

    @Override
    public int compare(Object t, Object t1) {
        Animal obj1, obj2;
        obj1 = (Animal) t;
        obj2 = (Animal) t1;
        int b1 = obj1.getBrinco();
        int b2 = obj2.getBrinco();
        
        if(b1==b2){
            return 0;
        }else if(b1<b2){   
            return -1;                    
        }else{
            return 1;
        }
        
    }
    
}
