package com.curso.mc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Categoria;
import com.curso.mc.domain.Cidade;
import com.curso.mc.domain.Cliente;
import com.curso.mc.domain.Endereco;
import com.curso.mc.domain.Estado;
import com.curso.mc.domain.ItemPedido;
import com.curso.mc.domain.Pagamento;
import com.curso.mc.domain.PagamentoComBoleto;
import com.curso.mc.domain.PagamentoComCartao;
import com.curso.mc.domain.Pedido;
import com.curso.mc.domain.Produto;
import com.curso.mc.domain.enuns.EstadoPagamento;
import com.curso.mc.domain.enuns.TipoCliente;
import com.curso.mc.repositories.CategoriaRepository;
import com.curso.mc.repositories.CidadeRepository;
import com.curso.mc.repositories.ClienteRepository;
import com.curso.mc.repositories.EnderecoRepository;
import com.curso.mc.repositories.EstadoRepository;
import com.curso.mc.repositories.ItemPedidoRepository;
import com.curso.mc.repositories.PagamentoRepository;
import com.curso.mc.repositories.PedidoRepository;
import com.curso.mc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	public void instantieteTestDataBase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null,"Cama mesa e banho");
		Categoria cat4 = new Categoria(null,"Eletrônicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");

		Produto p1 = new Produto(null, "Computador", 20000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "Tv True Color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);		

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p3,p2,p1,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		String t1 = "27363323";
		String t2 = "93838393";
		cl1.getTelefones().addAll(Arrays.asList(t1, t2));

		Endereco end1 = new Endereco(null, "Rua das Flores", "300", "Apto 300", "Jardim", "38220834", c1, cl1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", c2, cl1);
		cl1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, end2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2.000);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
