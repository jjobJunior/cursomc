package com.jobJunior.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jobJunior.cursomc.model.Categoria;
import com.jobJunior.cursomc.model.Cidade;
import com.jobJunior.cursomc.model.Cliente;
import com.jobJunior.cursomc.model.Endereco;
import com.jobJunior.cursomc.model.Estado;
import com.jobJunior.cursomc.model.ItemPedido;
import com.jobJunior.cursomc.model.Pagamento;
import com.jobJunior.cursomc.model.PagamentoComBoleto;
import com.jobJunior.cursomc.model.PagamentoComCartao;
import com.jobJunior.cursomc.model.Pedido;
import com.jobJunior.cursomc.model.Produto;
import com.jobJunior.cursomc.model.enuns.EstadoPagamento;
import com.jobJunior.cursomc.model.enuns.TipoCliente;
import com.jobJunior.cursomc.repositories.CategoriaRepository;
import com.jobJunior.cursomc.repositories.CidadeRepository;
import com.jobJunior.cursomc.repositories.ClienteRepository;
import com.jobJunior.cursomc.repositories.EnderecoRepository;
import com.jobJunior.cursomc.repositories.EstadoRepository;
import com.jobJunior.cursomc.repositories.ItemPedidoRepository;
import com.jobJunior.cursomc.repositories.PagamentoRepository;
import com.jobJunior.cursomc.repositories.PedidoRepository;
import com.jobJunior.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Banco de Dados");
		Categoria cat3 = new Categoria(null, "Engenharia de software");
		Categoria cat4 = new Categoria(null, "Lógica de programação");
		Categoria cat5 = new Categoria(null, "Algoritmos");
		Categoria cat6 = new Categoria(null, "Mátematica");
		Categoria cat7 = new Categoria(null, "Inglês");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 35.00);
		Produto p4 = new Produto(null, "Toalha", 22.00);
		Produto p5 = new Produto(null, "liquidificador", 153.00);
		Produto p6 = new Produto(null, "Televisão", 2798.00);
		Produto p7 = new Produto(null, "Microondas", 465.00);
		Produto p8 = new Produto(null, "Teclado", 70.00);
		Produto p9 = new Produto(null, "Panela", 74.00);
		Produto p10 = new Produto(null, "Aquecedor", 487.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p4, p5));
		cat4.getProdutos().addAll(Arrays.asList(p6, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p6, p7));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat4, cat7));
		p7.getCategorias().addAll(Arrays.asList(cat4, cat7));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Frutal", est1);
		Cidade cid2 = new Cidade(null, "Planura", est1);
		Cidade cid3 = new Cidade(null, "Tarumã", est2);

		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		est2.getCidades().addAll(Arrays.asList(cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "JOb Junior", "jobpsjunior@gmail.com", "35345702861",
				TipoCliente.PESSOAFISICA);
		cli1.getTelefoneSet().addAll(Arrays.asList("15264574", "30256896"));

		Endereco e1 = new Endereco(null, "Rua: Joao de Barros", "59", "Frente", "Vila dos Passaros", "19820-000", cli1,
				cid1);
		Endereco e2 = new Endereco(null, "Rua: Paranaiba", "35", "Fundo", "Vila dos Estados", "59873-000", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("11/01/2022 15:31"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("25/03/1987 12:31"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("11/01/2022 15:31"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
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
