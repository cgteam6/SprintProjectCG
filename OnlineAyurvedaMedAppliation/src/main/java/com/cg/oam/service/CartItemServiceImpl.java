package com.cg.oam.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.entities.Cart;
import com.cg.oam.entities.CartItem;
import com.cg.oam.entities.Medicine;
import com.cg.oam.exception.ItemNotFoundException;
import com.cg.oam.exception.MedicineNotFoundException;
import com.cg.oam.repository.CartItemRepository;
import com.cg.oam.repository.MedicineRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	CartItemRepository cartItemRepo;
	@Autowired
	MedicineRepository medRepo;
	Logger logger = LoggerFactory.getLogger(CartItemService.class);

	/* The addItemToCart method adds Cart Item to cart */
	public CartItem addItemToCart(Cart cart, Medicine medicine) {
		CartItem item = new CartItem();

		for (CartItem cartitems : cart.getItems()) {
			if (cartitems.getMedicine().getMedicineName().equals(medicine.getMedicineName())) {
				cartitems.setQuantity(cartitems.getQuantity() + 1);
				item = cartitems;

			} else {
				CartItem cartItem = new CartItem();
				cartItem.setMedicine(medicine);
				cartItem.setQuantity(1);
				cart.getItems().add(cartItem);
				item = cartItem;

			}

		}
		return cartItemRepo.save(item);

	}

	public CartItem decreaseQuantity(Cart cart, Medicine medicine) throws ItemNotFoundException {
		CartItem item = new CartItem();
		for (CartItem cartitems : cart.getItems()) {
			if (!(cartitems.getMedicine().getMedicineName().equals(medicine.getMedicineName()))){
				throw new ItemNotFoundException("Item Not Found");
			} else {
				cartitems.setQuantity(cartitems.getQuantity() - 1);
				item = cartitems;
			}

		}
		return cartItemRepo.save(item);

	}

	public List<CartItem> getAllCartItem(Cart cart) {
		List<CartItem> allItems = cart.getItems();
		return allItems;
	}

}
