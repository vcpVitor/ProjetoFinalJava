package View;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.*;

import Plataforma.Comprador;
import Plataforma.Veiculo;
import Plataforma.Venda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ContainerCatalogo implements ActionListener {
	private ImageIcon image;
	private Box box;
	public Veiculo v;
	
	public ContainerCatalogo(Veiculo v) {
		this.v = v;
		this.image = new ImageIcon(new ImageIcon(v.getImagem()).getImage().getScaledInstance(200, 140, Image.SCALE_DEFAULT));
		box = Box.createVerticalBox();
		
		JButton button = new JButton("Visualizar");
//		box.addMouseListener(new VeiculoCatalogoMouseListener(v));
		button.addActionListener(this);
		
		box.add(new JLabel(image, SwingConstants.CENTER));
		box.add(new JLabel(v.getNome(), SwingConstants.CENTER));
		box.add(new JLabel(String.valueOf(v.getAno()), SwingConstants.CENTER));
		box.add(button);
//        box.setBounds(50, 120, 200, 250);
	}
	
	public Box getBox() {
		return box;
	}
	/**
	 * Coloca as informações principais do veiculo em um container para ser vizualizado 
	 */
	
	public void actionPerformed(ActionEvent e) {
		JanelaPrincipal.clear();
		JanelaPrincipal.showDetails(v);
	}
}

