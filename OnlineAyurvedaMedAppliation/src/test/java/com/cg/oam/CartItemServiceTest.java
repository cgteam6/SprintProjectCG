package com.cg.oam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.oam.entities.Cart;
import com.cg.oam.entities.CartItem;
import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Medicine;
import com.cg.oam.exception.EmptyCartException;
import com.cg.oam.exception.ItemNotFoundException;
import com.cg.oam.repository.CartItemRepository;
import com.cg.oam.service.CartItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartItemServiceTestClass {
	@Autowired
	private CartItemService cartItemService;
	@MockBean
	private CartItemRepository cartItemRepository;

	@Test
	public void addItemTocart() {
		Medicine ashwagandha = new Medicine(101, "Ashwagandha", 50, LocalDate.of(2019, 02, 20),
				LocalDate.of(2020, 02, 20), "Oushadhi");
		CartItem item1 = new CartItem(101, ashwagandha, 10);
		List<CartItem> itemList = new ArrayList<>();
		itemList.add(item1);
		Customer customer = new Customer(101, "Vishnu", "Vishnu10");
		Cart cart = new Cart(101, customer, itemList);
		when(cartItemRepository.save(item1)).thenReturn(item1);
		assertEquals(item1, cartItemService.addItemToCart(cart, ashwagandha));
		System.out.println("*****ADD ITEM  TESTCASE 1 PASSED ******");
	}

	@Test
	public void decreaseQuantity() throws ItemNotFoundException {
		Medicine ashwagandha = new Medicine(101, "Ashwagandha", 50, LocalDate.of(2019, 02, 20),
				LocalDate.of(2020, 02, 20), "Oushadhi");
		CartItem item1 = new CartItem(101, ashwagandha, 10);
		List<CartItem> itemList = new ArrayList<>();
		itemList.add(item1);
		Customer customer = new Customer(101, "Vishnu", "Vishnu10");
		Cart cart = new Cart(101, customer, itemList);
		when(cartItemRepository.save(item1)).thenReturn(item1);
		assertEquals(item1, cartItemService.decreaseQuantity(cart, ashwagandha));
		System.out.println("*****DECREASE ITEM QUANTITY  TESTCASE 1 PASSED ******");

	}

	@Test
	public void decreaseQuantityNoItem() throws ItemNotFoundException {
		Medicine ashwagandha = new Medicine(101, "Ashwagandha", 50, LocalDate.of(2019, 02, 20),
				LocalDate.of(2020, 02, 20), "Oushadhi");
		Medicine brahmi = new Medicine(102, "Brahmi", 50, LocalDate.of(2019, 02, 20), LocalDate.of(2020, 02, 20),
				"Oushadhi");

		CartItem item1 = new CartItem(101, ashwagandha, 20);
		List<CartItem> itemList = new ArrayList<>();
		itemList.add(item1);
		Customer customer = new Customer(101, "Vishnu", "Vishnu10");
		Cart cart = new Cart(101, customer, itemList);
		assertThrows("Item Not Found Exception must be Thrown", ItemNotFoundException.class,
				() -> cartItemService.decreaseQuantity(cart, brahmi));
		System.out.println("*****DECREASE ITEM QUANTITY  TESTCASE 2 PASSED ******");

	}
	@Test
	public void getAllCartItems() throws EmptyCartException {
		Medicine ashwagandha = new Medicine(101, "Ashwagandha", 50, LocalDate.of(2019, 02, 20),
				LocalDate.of(2020, 02, 20), "Oushadhi");
		Medicine brahmi = new Medicine(102, "Brahmi", 50, LocalDate.of(2019, 02, 20), LocalDate.of(2020, 02, 20),
				"Oushadhi");
		CartItem item1 = new CartItem(101, ashwagandha, 20);
		CartItem item2 = new CartItem(102, brahmi, 25);
		List<CartItem> itemList = new ArrayList<>();
		itemList.add(item1);
		itemList.add(item2);
		Customer customer = new Customer(101, "Vishnu", "Vishnu10");
		Cart cart = new Cart(101, customer, itemList);
		when(cartItemRepository.findAll()).thenReturn(itemList);
		assertEquals(itemList, cartItemService.getAllCartItem(cart));
		System.out.println("*****GET ALL CART ITEM TESTCASE 1 PASSED ******");
		
	}
	@Test
	public void getAllCartItemsCartEmpty() {
		Medicine ashwagandha = new Medicine(101, "Ashwagandha", 50, LocalDate.of(2019, 02, 20),
				LocalDate.of(2020, 02, 20), "Oushadhi");
		Medicine brahmi = new Medicine(102, "Brahmi", 50, LocalDate.of(2019, 02, 20), LocalDate.of(2020, 02, 20),
				"Oushadhi");
		CartItem item1 = new CartItem(101, ashwagandha, 20);
		CartItem item2 = new CartItem(102, brahmi, 25);
		List<CartItem> itemList = new ArrayList<>();
		Customer customer = new Customer(101, "Vishnu", "Vishnu10");
		Cart cart = new Cart(101, customer, itemList);
		assertThrows("Cart Empty Exception must be Thrown", EmptyCartException.class,
				() -> cartItemService.getAllCartItem(cart));
		
		System.out.println("*****GET ALL CART ITEM TESTCASE 2 PASSED ******");
		
	}

}
