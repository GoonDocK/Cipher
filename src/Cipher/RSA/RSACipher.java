package Cipher.RSA;
import Miscellaneous.Exceptions.InvalidCharacter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
/* disclaimer: This class is not optimized, it is just a proof of concept, also JAVA implements RSA Algorithm by default */

public class RSACipher {
    private long cipher;
    private long phi;
    private long exponent;
    private long Z;
    private long prime1;
    private long prime2;
    private long N;
    private long S;
    private long Message;

    public void setPrimes(long prime1, long prime2){
        if(!checkPrimes(prime1)) throw new IllegalArgumentException(prime1+" No es un numero primo");
        if(!checkPrimes(prime2)) throw new IllegalArgumentException(prime2+" No es un numero primo");
        this.Z=prime1*prime2;
        this.prime1=prime1;
        this.prime2=prime2;
        this.phi=(prime1-1)*(prime2-1);
    }
    public void setN(long N){
        if(N>this.Z) throw new InvalidCharacter("N debe ser menor que Z");
        if(N<=0) throw new InvalidCharacter("N debe ser mayor que 0");
        long big = Math.max(N, this.phi);
        long small = Math.min(N, this.phi);

        while (small != 0) {
            long temp = small;
            small = big % small;
            big = temp;
        }
        if (big != 1) {
            throw new InvalidCharacter(
                    "Phi y N deben ser coprimos"
            );
        }
        this.N=N;
    }
    private boolean checkPrimes(long prime1){
        if(prime1<=1) return false;
        int dividers=0;
        for(int i=2;i<=Math.sqrt(prime1);i++){
            if(dividers>0) break;
            if(prime1%i==0) dividers++;
        }
        return dividers == 0;
    }
    private void calculateExponent(){
        ArrayList<Long> factors=primeFactorDecomposition(this.phi);
        double factor=1;
        for (Long aLong : factors) {
            factor = factor * (1-(float) 1 / aLong);
        }
        this.exponent= Math.round((this.phi*factor))-1;
    }
    //Calculates the prime factor decomposition of a number, but it is extremely unoptimized and requires calculating all the primes before Z, working on a better alternative
    private ArrayList<Long> primeFactorDecomposition(long number){
        ArrayList<Long> factors=new ArrayList<>();
        long prime=2;
        while(number!=1){
            if(number%prime==0){
                number=number/prime;
                if(!factors.contains(prime)){
                    factors.add(prime);
                }
            }else{
                prime=nextFactor(prime);
            }
        }
        return factors;
    }
    private long nextFactor(long previousFactor){
        long nextFactor=previousFactor+1;
        while(!checkPrimes(nextFactor)){
            nextFactor++;
        }
        return nextFactor;
    }
    private long fastExponentiation(long base, long exponent, long modulus){
        ArrayList<Integer> binaryBase=toBinary(exponent);
        long X=1;
        for(Integer i: binaryBase){
            if(i==1){
                X=(((X*X)*base)%modulus);
            }else{
                X =((X*X)%modulus);
            }
        }
        return X;
    }
    private ArrayList<Integer> toBinary(long number){
        ArrayList<Integer> binary=new ArrayList<>();
       while(!(number<2)){
           if(number%2==0){
               binary.add(0);
           }else{
               binary.add(1);
           }
           number=number/2;
       }
       binary.add(1);
       Collections.reverse(binary);
       return binary;
    }
    public void encrypt(){
        calculateExponent();
        S=fastExponentiation(this.N,this.exponent,this.phi);
        this.cipher=fastExponentiation(this.Message,this.N,this.Z);
    }
    // This Method is used for calculating prime numbers below a specific number
    private ArrayList<Long> getPrimeNumbers(long limit){
        ArrayList<Long> primes=new ArrayList<>();
        for(long i=2;i<limit;i++){
            if(checkPrimes(i)){
                primes.add(i);
            }
        }
        return primes;
    }
    // Brute force method for finding the prime numbers that factorize Z can take extremely long time if Z is too big
    private void crackPrimes(long Z){
        ArrayList<Long> primes=getPrimeNumbers(Z);
        boolean found=false;
        for(Long prime: primes){
            for(Long prime1: primes){
                if(prime*prime1==Z){
                    setPrimes(prime,prime1);
                    found=true;
                    break;
                }
            }
            if(found) break;
        }
        if(!found) throw new InvalidCharacter("No se encontro el factor de Z");
    }
    public long getPrime1(){
        return this.prime1;
    }
    public long getPrime2(){
        return this.prime2;
    }
    public long getN(){
        return this.N;
    }
    public long getS(){
        return this.S;
    }
    public long getZ(){
        return this.Z;
    }
    public long getPhi(){
        return this.phi;
    }
    public void setS(long S){
        if(S==0) crackPrimes(this.Z);
        calculateExponent();
    }
    public long getExponent(){
        return this.exponent;
    }
    public long decrypt(){
        return fastExponentiation(this.cipher,this.S,this.Z);
    }
    public void setMessage(long Message ){
        this.Message=Message;
    }
    public long getMessage(){
        this.S=fastExponentiation(this.N,this.exponent,this.phi);
        this.Message=decrypt();
        return this.Message;
    }
    public void setCipher(long cipher){
        this.cipher=cipher;
    }
    public long getCipher(){
        encrypt();
        return this.cipher;
    }
    public void setZ(long Z){
        this.Z=Z;
    }
    public void clear(){
        this.cipher=0;
        this.S=0;
        this.Message=0;
        this.Z=0;
        this.prime1=0;
        this.prime2=0;
        this.N=0;
        this.phi=0;
        this.exponent=0;
    }
}
