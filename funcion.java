/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p_metodos_grafico;
/**
 *
 * @author graciela
 */
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

public class funcion {
    private DJep j;
    private String f;

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }
    
    public funcion(String f){
        j = new DJep();
        this.f = f;
        j.addStandardConstants();
        //agrega constantes estandares, pi, e, etc
        j.addStandardFunctions();
        //agrega funciones estandares cos(x), sin(x)
        j.addComplex();
        //por si existe algun numero complejo
        j.setAllowUndeclared(true);
        //permite variables no declarables
        j.setAllowAssignment(true);
        //permite asignaciones
        j.setImplicitMul(true);
        //regla de multiplicacion o para sustraccion y sumas
        j.addStandardDiffRules();
    }
    
    public Double Evaluar(double x) throws ParseException{
        Node re = null;
        String val="No";
        j.addVariable("x", x);
        try{
            re = j.parse(f);  //esta funcion parse es la que en si hace el proceso de la evaluacion de la expresion
        } catch (ParseException ex) {
            
        }
        val = (j.evaluate(re)).toString();
        return Double.parseDouble(val);
    }
    
    
    public String Derivada(String x){
        String derivada = "No";
        try{
            //coloca el nodo con una funcion preestablecida
            Node node = j.parse(f);
            //deriva la funcion con respecto a x
            Node diff = j.differentiate(node,"x");
            //Simplificamos la funcion con respecto a x
            Node simp = j.simplify(diff);
            //Convertimos el valor simplificado en un String
            derivada =j.toString(simp);
        }catch(ParseException e){ 
           
        }
        return derivada;
    }
    
    public boolean isNum(String num){
        char []ar = num.toCharArray();
        for(int i = 0; i < ar.length; i++){
            if(Character.isLetter(ar[i]))
                return false;
        }
        return true;
    }
}