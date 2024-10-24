package com.generation.mondolibri.service.implementation;

import com.generation.mondolibri.exceptions.checked.AuthenticationException;
import com.generation.mondolibri.exceptions.checked.UserAlreadyExistsException;
import com.generation.mondolibri.exceptions.checked.UserNotFoundException;
import com.generation.mondolibri.model.entity.Cart;
import com.generation.mondolibri.model.entity.User;
import com.generation.mondolibri.repository.jpa.CartRepository;
import com.generation.mondolibri.repository.jpa.UserRepository;
import com.generation.mondolibri.util.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.function.Function;

/**
 * Service class for managing operations related to users.
 * Extends AbstractService to leverage common transactional and CRUD operations.
 */
@Service
public class UserService extends AbstractService<User,Integer> {

    private final CartRepository cartRepository;

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository) {
        super(userRepository);
        this.cartRepository = cartRepository;
    }
    private void allowRegister(User toBeRegistered) throws UserAlreadyExistsException {
        // Check if there's an existing user with the same username
        if (((UserRepository) repository).findByUsername(toBeRegistered.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already in use");
        }

        // Check if there's an existing user with the same email
        if (((UserRepository) repository).findByEmail(toBeRegistered.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already in use");
        }
    }
    /**
     * Registers a new user in the system.
     *
     * @param toBeRegistered User object containing registration details.
     * @return The registered User object.
     */
    @Transactional(rollbackFor = UserNotFoundException.class)
    public User register(User toBeRegistered) throws UserAlreadyExistsException {
        allowRegister(toBeRegistered);
        User registeredUser = repository.save(toBeRegistered);
        Cart userCart = cartRepository.save(new Cart(registeredUser)); // Create cart for the registered user
        registeredUser.setCart(userCart); // Set the created cart to the registered user
        return registeredUser;
    }

    /**
     * Authenticates a user based on username and password.
     *
     * @param username        Username of the user attempting to log in.
     * @param password        Password of the user attempting to log in.
     * @return Authenticated User object if successful.
     * @throws AuthenticationException if authentication fails (invalid username or password).
     */
    @Transactional(readOnly = true)
    public User loginWithUser(String username, String password)
            throws UserNotFoundException, AuthenticationException, NoSuchAlgorithmException {
        return login(username, password, ((UserRepository) repository)::findByUsername);
    }

    /**
     * Authenticates a user based on email and password.
     *
     * @param email           Email address of the user attempting to log in.
     * @param password        Password of the user attempting to log in.
     * @return Authenticated User object if successful.
     * @throws AuthenticationException if authentication fails (invalid email or password).
     */
    @Transactional(readOnly = true)
    public User loginWithEmail(String email, String password)
            throws UserNotFoundException, AuthenticationException, NoSuchAlgorithmException {
        return login(email, password, ((UserRepository) repository)::findByEmail);
    }

    /**
     * Performs user authentication based on a generic identifier (username or email).
     *
     * @param identifier      Identifier (username or email) of the user attempting to log in.
     * @param password        Password of the user attempting to log in.
     * @param findByIdentifier Function to find a user by identifier (username or email).
     * @return Authenticated User object if successful.
     * @throws AuthenticationException if authentication fails (invalid identifier or password).
     */
    @Transactional
    protected User login(String identifier, String password,
                         Function<String, Optional<User>> findByIdentifier)
            throws UserNotFoundException, AuthenticationException, NoSuchAlgorithmException {

            User user = findByIdentifier
                    .apply(identifier)
                    .orElseThrow(() -> new UserNotFoundException("User not found with the provided identifier"));

            // Verify password
            if (!PasswordHasher.comparePassword(password, user.getPassword())) {
                throw new AuthenticationException("Incorrect password");
            }
            return user;
    }

    @Transactional(readOnly = true)
    public Cart GetRelatedCart(Integer id) throws UserNotFoundException {
            return findById(id)
                    .orElseThrow(()-> new UserNotFoundException("The requested user was not found"))
                    .getCart();
    }


    public User findByUsername(String name) throws UserNotFoundException {
        return ((UserRepository) repository).findByUsername(name)
                .orElseThrow(()->new UserNotFoundException("User with the requested username not found"));
    }
}
