package xyz.chichistudy.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.chichistudy.springboot.entity.Order;
import xyz.chichistudy.springboot.entity.OrderItem;
import xyz.chichistudy.springboot.repository.CustomerRepository;
import xyz.chichistudy.springboot.repository.EmployeeRepository;
import xyz.chichistudy.springboot.repository.OrderItemRepository;
import xyz.chichistudy.springboot.repository.OrderRepository;
import xyz.chichistudy.springboot.repository.ProductRepository;
import xyz.chichistudy.springboot.validator.InventoryValidator;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private InventoryValidator InventoryValidator;
	
	/* 訂單主檔
	 * --------------------------------------------------------------------
	 * GET  -> /            -> index
	 * GET  -> /{id}        -> get
	 * GET  -> /delete/{id} -> delete
	 * POST -> /            -> add
	 * PUT  -> /            -> update
	 ----------------------------------------------------------------------
	 * 訂單細目
	 * --------------------------------------------------------------------
	 * GET  -> /{oid}/item              -> viewItem
	 * GET  -> /{oid}/item/{iid}        -> getItem
	 * GET  -> /{oid}/item/delete/{iid} -> deleteItem
	 * POST -> /{oid}/item              -> addItem
	 * PUT  -> /{oid}/item              -> updateItem
	 ---------------------------------------------------------------------*/
	@GetMapping("/")
	public String index(
		@ModelAttribute Order order,
		Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("orders", orderRepository.findAll());
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());
		return "order";
	}
	@GetMapping("/{id}")
	public String get(
		@PathVariable("id") Long id,
		Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("order", orderRepository.findById(id));
		model.addAttribute("orders", orderRepository.findAll());
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("employees", employeeRepository.findAll());
		return "order";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(
		@PathVariable("id") Long id) {
		orderRepository.deleteById(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(
		@Valid @ModelAttribute Order order,
		BindingResult result,
		Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("orders", orderRepository.findAll());
			model.addAttribute("customers", customerRepository.findAll());
			model.addAttribute("employees", employeeRepository.findAll());
		}
		orderRepository.save(order);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(
		@Valid @ModelAttribute Order order,
		BindingResult result,
		Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("orders", orderRepository.findAll());
			model.addAttribute("customers", customerRepository.findAll());
			model.addAttribute("employees", employeeRepository.findAll());
		}
		orderRepository.save(order);
		return "redirect:./";
	}
	
	/* 訂單細目 */
	
	@GetMapping("/{oid}/item")
	public String viewItem(
		Model model,
		@PathVariable("oid") Long oid,
		@ModelAttribute OrderItem orderItem) {
		model.addAttribute("order", orderRepository.findById(oid).get());
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("_method", "POST");
		return "orderitem";
	}
	
	@GetMapping("/{oid}/item/{iid}")
	public String getItem(
		Model model,
		@PathVariable("oid") Long oid,
		@PathVariable("iid") Long iid) {
		model.addAttribute("order", orderRepository.findById(oid).get());
		model.addAttribute("orderItem", orderItemRepository.findById(iid).get());
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("_method", "PUT");
		return "orderitem";
	}
	
	@GetMapping("/{oid}/item/delete/{iid}")
	public String deleteItem(
		@PathVariable("iid") Long iid) {
		orderItemRepository.deleteById(iid);
		return "redirect:../";
	}
	
	@PostMapping("/{oid}/item")
	public String addItem(
		OrderItem orderItem,
		@PathVariable("oid") Long oid,
		BindingResult result,
		Model model)  {
		InventoryValidator.validate(orderItem, result);
		if(result.hasErrors()) {
			model.addAttribute("order", orderRepository.findById(oid).get());
			model.addAttribute("orderItem", orderItem);
			model.addAttribute("products", productRepository.findAll());
			model.addAttribute("_method", "POST");
			return "orderitem";
		}
		orderItemRepository.save(orderItem);
		return "redirect:./item";
	}
	
	@PutMapping("/{oid}/item")
	public String updateItem(
		OrderItem orderItem,
		@PathVariable("oid") Long oid,
		BindingResult result,
		Model model)  {
		InventoryValidator.validate(orderItem, result);
		if(result.hasErrors()) {
			model.addAttribute("order", orderRepository.findById(oid).get());
			model.addAttribute("orderItem", orderItem);
			model.addAttribute("products", productRepository.findAll());
			model.addAttribute("_method", "PUT");
			return "orderitem";
		}
		orderItemRepository.save(orderItem);
		return "redirect:./item";
	}
	
}