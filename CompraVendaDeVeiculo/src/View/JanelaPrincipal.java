package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.FilteredImageSource;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import java.awt.GridLayout;


import Plataforma.Carro;
import Plataforma.Catalogo;
import Plataforma.Comprador;
import Plataforma.Veiculo;
import Plataforma.Venda;
import Plataforma.Vendedor;

public class JanelaPrincipal implements ActionListener{
	public static JFrame mainframe;
	public static JPanel panelCatalogo = new JPanel();
	public static JanelaPrincipal j;
	private static JButton cadastro = new JButton("Cadastrar");
	private static JButton cadastroPessoa = new JButton("Cadastrar Pessoa");
	private static JButton botaoComprar = new JButton("Comprar");
	private static JButton botaoVoltarDetails = new JButton("Voltar");
	private static Veiculo selectedVeiculo;
	private static Vendedor vendedor;
	private static Comprador comprador;
	private static Catalogo catalogo;
	private static Catalogo filterCatalogo;
	private static JButton botaofiltros = new JButton("Filtrar");
	private static JButton cleanfiltro = new JButton("Limpar Filtros");
	
	public JanelaPrincipal(Vendedor v1) {
		
		
		comprador = new Comprador("Trevor",2,32,"32");
		vendedor = v1;
		j = this;
				
		mainframe = new JFrame("Catálogo");
		mainframe.setSize(1280, 720);
		
		cadastro.addActionListener(this);
		cadastroPessoa.addActionListener(this);
		
		botaofiltros.addActionListener(this);
		botaofiltros.setLocation(302, 0);
		botaofiltros.setSize(120, 40);
		
		cleanfiltro.addActionListener(this);
		cleanfiltro.setLocation(423, 0);
		cleanfiltro.setSize(120,40);
		
		cadastroPessoa.setLocation(121,0);
		cadastroPessoa.setSize(180,40);
		
		cadastro.setLocation(0, 0);
		cadastro.setSize(120,40);
		
		panelCatalogo.setBounds(0, 0, 1280, 720);
		panelCatalogo.setLayout(new GridLayout(0,4));
		
		mainframe.add(botaofiltros);
		mainframe.add(cadastro);
		mainframe.add(cadastroPessoa);
		mainframe.add(cleanfiltro);
		

		JScrollPane painelScroll = new JScrollPane(panelCatalogo);
		mainframe.add(painelScroll);		
	}
	/**
	 * Janela principal contendo todos os botões necessários e sistema de filtragem e catalogo
	 */

	public void showPaginaInicial() {
//		clear();
		botaoComprar.removeActionListener(j);
		botaoVoltarDetails.removeActionListener(j);
		showCatalogo();
		showMainButtons();
		mainframe.repaint();
	}
	
	/**
	 * Mostra a pagina inicial
	 */
	public void showMainButtons() {
		cadastro.setVisible(true);
		cadastroPessoa.setVisible(true);
	}
	/**
	 * Torna os botões visiveis
	 * 
	 */
	public static void hideMainButtons() {
		cadastro.setVisible(false);
		cadastroPessoa.setVisible(false);
	}
	
	/**
	 * Oculta os botoes
	 */
	
	public static void clear() {
		panelCatalogo.removeAll();
		panelCatalogo.revalidate();
		mainframe.repaint();
	}
	/**
	 * limpa o catalogo
	 * @param v
	 */
	public static void showDetails(Veiculo v) {
		hideMainButtons();
		
		selectedVeiculo = v;
		ImageIcon image = new ImageIcon(new ImageIcon(v.getImagem()).getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
		
		Box box = Box.createHorizontalBox();
		Box inBox = Box.createVerticalBox();
		
		inBox.add(new JLabel("   Marca: " + v.getMarca()));
		inBox.add(new JLabel("   Nome: " + v.getNome()));
		inBox.add(new JLabel("   Ano: " + String.valueOf(v.getAno())));
		inBox.add(new JLabel("   Preco: " + String.valueOf(v.getPreco())));
		
		
		JLabel painel = new JLabel();
		painel.setLayout(new BorderLayout());
		JLabel poup = new JLabel("   Telefone: " + String.valueOf(v.getVendedor().getTelefone()));
		inBox.add(poup);
		poup.setLayout(new BorderLayout());
		painel.add(botaoComprar, BorderLayout.SOUTH);
		
		inBox.add(botaoComprar);
		botaoComprar.addActionListener(j);
		
		inBox.add(botaoVoltarDetails);
		botaoVoltarDetails.addActionListener(j);
		
		box.add(new JLabel(image, SwingConstants.LEFT));
		box.add(inBox);

		panelCatalogo.add(box);
		mainframe.repaint();
		
	}
	/**
	 * Mostra os principais detalhes do veiculo e oferece o botão de compra
	 * 
	 * @param cat
	 */
	public void addContainerCatalogo(Catalogo cat) {
		catalogo = cat;
		filterCatalogo = catalogo;
	}
	/**
	 * adiciona o container do catalogo
	 * 
	 */
	 
	public void resetFilter() {
		filterCatalogo = catalogo;
		showCatalogo();
	}
	/**
	 * reseta o filtro
	 * 
	 */
	
	public void showCatalogo() {
		Veiculo[] veiculos = filterCatalogo.getListaVeiculos();
		panelCatalogo.removeAll();
		for (int i = 0; i < filterCatalogo.length(); i++) {
			if (!veiculos[i].getVendido()) {
				panelCatalogo.add(new ContainerCatalogo(veiculos[i]).getBox());				
			}
		}
		panelCatalogo.revalidate();
		panelCatalogo.repaint();
	}
	/**
	 * mostra os veiculos 
	 * 
	 */
	
	public void setVisible() {
		showPaginaInicial();
		mainframe.setVisible(true);
	}
	public static void showRecibo(Venda recibo) {
		JPanel poup = new JPanel();
		JLabel vend = new JLabel("   Vendedor: " + recibo.getVeiculo().getVendedor().getNome());
		JLabel comp = new JLabel("   Comprador: " + recibo.getComprador().getNome());
		JLabel vei = new JLabel("   Veiculo: " + recibo.getVeiculo().getNome());
		poup.add(vend);
		poup.add(comp);
		poup.add(vei);
		
		
		
		panelCatalogo.add(poup);
		mainframe.repaint();
		
	}
	/**
	 * mostra o recibo de compra do veiculo
	 * 
	 */

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src.equals(cadastro)) {
			realizarCadastroVeiculo();
		}
		else if (src.equals(botaoComprar)) {
			
			
			JLabel comprante = new JLabel("Comprador: "+comprador.getNome());
			JLabel comprado = new JLabel ("Veiculo: "+ selectedVeiculo.getNome());
	    	
			JPanel panel = new JPanel(new GridLayout(0,1));
			panel.add(comprante);
			panel.add(comprado);
			
			int result = JOptionPane.showConfirmDialog(null, panel, "Finalizar Compra?",
		     		   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if (result == JOptionPane.OK_OPTION) {
				selectedVeiculo.setVendido(true);
				
				JPanel recibo = new JPanel(new GridLayout(0,1));	
				JLabel reciboComp = new JLabel ("Comprador:" +comprador.getNome());
				JLabel rebiboVend  = new JLabel ("Vendedor: " + vendedor.getNome());
				JLabel reciboVeic = new JLabel ("Veiculo: " + selectedVeiculo.getNome());
				JLabel reciboPrec = new JLabel ("Preço: " + selectedVeiculo.getPreco());
				
				recibo.add(reciboComp);
				recibo.add(rebiboVend);
				recibo.add(reciboVeic);
				recibo.add(reciboPrec);
				
				JOptionPane.showMessageDialog(null, recibo, "Recibo", JOptionPane.PLAIN_MESSAGE);
			}
			/**
			 * realiza a finalização da compra
			 */
			
			
			
//			JanelaPrincipal.clear();
//			Venda vend = new Venda(431, new Comprador("Gabriel", 432, 321, "72312"), selectedVeiculo);
//			JanelaPrincipal.showRecibo(vend);
		}
		
		else if (src.equals(cadastroPessoa)) {
			realizarCadastroPessoa();
			
		}
		
		else if (src.equals(botaoVoltarDetails)) {
			showPaginaInicial();
		}
		
		else if (src.equals(cleanfiltro)) {
			resetFilter();
			
		}
		
		else if (src.equals(botaofiltros)) {
			
			JTextField minimo = new JTextField("-1");
			JTextField maximo = new JTextField("99999999");
			JTextField marca = new JTextField();
			
			JPanel filtrador = new JPanel(new GridLayout(0,1));
			filtrador.add(new JLabel("Preço Minimo:"));
			filtrador.add(minimo);
			filtrador.add(new JLabel("Preço Maximo:"));
			filtrador.add(maximo);
			filtrador.add(new JLabel("Escreva a marca: "));
			filtrador.add(marca);
			
			
			int result = JOptionPane.showConfirmDialog(null, filtrador, "Finalizar Filtragem",
		     		   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
			if (result == JOptionPane.OK_OPTION) {
				int min = Integer.parseInt(minimo.getText());
				int max = Integer.parseInt(maximo.getText());
				String marca_veiculo = marca.getText();
				
				filterCatalogo = Catalogo.filtrarPorPreco(filterCatalogo, min, max);
				if (!marca_veiculo.isEmpty()) {
					filterCatalogo = Catalogo.filtrarPorMarca(filterCatalogo, marca_veiculo);
				}
				
				showPaginaInicial();
			}
			
		}
		
	}
	/**
	 * cria uma tela com os filtros por intervalo de preço e por marca
	 */
	
	
	
	private void realizarCadastroPessoa() {
		JTextField nome = new JTextField();
		JTextField cpf = new JTextField();
		JTextField telefone = new JTextField();
		JTextField endereco = new JTextField();
		
		String[] tipos = {"Vendedor", "Comprador"};
		JComboBox<String> tipoPessoa = new JComboBox<String>(tipos);
		
		
    	JPanel panel = new JPanel(new GridLayout(0,1));
    	panel.add(new JLabel("Insira o Nome:"));
    	panel.add(nome);
    	panel.add(new JLabel("Insira o CPF:"));
    	panel.add(cpf);
    	panel.add(new JLabel("Insira o Telefone:"));
    	panel.add(telefone);
    	panel.add(new JLabel("Insira o Endereço:"));
    	panel.add(endereco);
    	panel.add(new JLabel("Comprador ou Vendedor?:"));
    	panel.add(tipoPessoa);
    	
    	int result = JOptionPane.showConfirmDialog(null, panel, "Finalizar Cadastro",
     		   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    	
    	if (result == JOptionPane.OK_OPTION) {
    		if (tipoPessoa.getSelectedIndex() == 0) {
//    			Vendedor
    			vendedor = new Vendedor(nome.getText(), Integer.parseInt(cpf.getText()), Integer.parseInt(telefone.getText()), endereco.getText());
    		}
    		else {
//    			Comprador
    			comprador = new Comprador(nome.getText(), Integer.parseInt(cpf.getText()), Integer.parseInt(telefone.getText()), endereco.getText());	
    		}	
    	}

		
	}
	/**
	 * cria uma tela onde a pessoa se cadastra podendo ser ela um vendedor ou comprador
	 * 
	 */

	public void realizarCadastroVeiculo() {
		JTextField Capacifield = new JTextField();
    	JTextField Nomefield = new JTextField();
    	JTextField Ano = new JTextField();
    	JTextField Rodasfield = new JTextField();
    	JTextField Combusfield = new JTextField();
    	JTextField Precofield = new JTextField();
    	JTextField KMfield = new JTextField();
    	JTextField Marcafield = new JTextField();
    	JTextField Portfield = new JTextField();
    	JTextField Carroceriafield = new JTextField();
    	JTextField Imagem = new JTextField();
    	
    	
    	JPanel panel = new JPanel(new GridLayout(0,1));
    	panel.add(new JLabel("Insira Capacidade de passageiros:"));
    	panel.add(Capacifield);
    	panel.add(new JLabel("Insira Ano:"));
    	panel.add(Ano);
    	panel.add(new JLabel("Insira Nome:"));
    	panel.add(Nomefield);
    	panel.add(new JLabel("Insira Numero de Rodas:"));
    	panel.add(Rodasfield);
    	panel.add(new JLabel("Insira Combustivel:"));
    	panel.add(Combusfield);
    	panel.add(new JLabel("Insira Preco:"));
    	panel.add(Precofield);
    	panel.add(new JLabel("Insira Quilometragem:"));
    	panel.add(KMfield);
    	panel.add(new JLabel("Insira Marca:"));
    	panel.add(Marcafield);
        panel.add(new JLabel("Insira Quantidade de Portas:"));
        panel.add(Portfield);
        panel.add(new JLabel("Insira Tipo de Carroceria:"));
        panel.add(Carroceriafield);
        
        panel.add(new JLabel("Insira Imagem"));
        panel.add(Imagem);
 
       
       int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Veiculo",
    		   JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
       if (result == JOptionPane.OK_OPTION) {
    	 Veiculo veiculo = new Carro();
    	 veiculo.setMarca(Marcafield.getText());
    	 veiculo.setPreco(Integer.parseInt(Precofield.getText()));
    	 veiculo.setImagem(Imagem.getText());
    	 veiculo.setAno(Integer.parseInt(Ano.getText()));
    	 veiculo.setNome(Nomefield.getText());
    	 veiculo.setVendedor(vendedor);
    	 catalogo.addVeiculo(veiculo);
    	 showCatalogo();
       }
	}
}

/**
 * realiza o cadastro de um veiculo
 * 
 */


	
