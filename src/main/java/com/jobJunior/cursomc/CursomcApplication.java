package com.jobJunior.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jobJunior.cursomc.modelo.Categoria;
import com.jobJunior.cursomc.modelo.Cidade;
import com.jobJunior.cursomc.modelo.Cliente;
import com.jobJunior.cursomc.modelo.Endereco;
import com.jobJunior.cursomc.modelo.Estado;
import com.jobJunior.cursomc.modelo.Pagamento;
import com.jobJunior.cursomc.modelo.PagamentoComBoleto;
import com.jobJunior.cursomc.modelo.PagamentoComCartao;
import com.jobJunior.cursomc.modelo.Pedido;
import com.jobJunior.cursomc.modelo.Produto;
import com.jobJunior.cursomc.modelo.enuns.EstadoPagamento;
import com.jobJunior.cursomc.modelo.enuns.TipoCliente;
import com.jobJunior.cursomc.repositories.CategoriaRepository;
import com.jobJunior.cursomc.repositories.CidadeRepository;
import com.jobJunior.cursomc.repositories.ClienteRepository;
import com.jobJunior.cursomc.repositories.EnderecoRepository;
import com.jobJunior.cursomc.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Banco de Dados");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 65.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

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
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
	}

}
