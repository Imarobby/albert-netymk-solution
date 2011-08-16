import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Test {
	public static void main(String[] args) {
		Random rand = new Random(47);
		int i = 0, j = 0;
		while(i <= 100){
		   	j = rand.nextInt(3);
			i += j;
			System.out.println(j);
		}
	}
}
