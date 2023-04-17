package com.geekbrains.spring_data1.controllers;


import com.geekbrains.spring_data1.converter.UserConverter;
import com.geekbrains.spring_data1.entites.User;
import com.geekbrains.spring_data1.exceptions.ResourceNotFoundException;
import com.geekbrains.spring_data1.repositories.UserRepository;
import com.geekbrains.spring_data1.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@Validated
@RequiredArgsConstructor
class UserController {
    private final UserRepository userRepository;

    private final UserConverter userConverter;
    private final UserService userService;

//    @GetMapping
//    public Page<User> all(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable, OAuth2Authentication authentication) {
//        String auth = (String) authentication.getUserAuthentication().getPrincipal();
//        String role = authentication.getAuthorities().iterator().next().getAuthority();
//        if (role.equals(User.Role.USER.name())) {
//            return repository.findAllByEmail(auth, pageable);
//        }
//        return repository.findAll(pageable);
//    }
//
//    @GetMapping("/search")
//    public Page<User> search(@RequestParam String email, Pageable pageable, OAuth2Authentication authentication) {
//        String auth = (String) authentication.getUserAuthentication().getPrincipal();
//        String role = authentication.getAuthorities().iterator().next().getAuthority();
//        if (role.equals(User.Role.USER.name())) {
//            return repository.findAllByEmailContainsAndEmail(email, auth, pageable);
//        }
//        return repository.findByEmailContains(email, pageable);
//    }
//
//    @GetMapping("/findByEmail")
//    @PreAuthorize("!hasAuthority('USER') || (authentication.principal == #email)")
//    public User findByEmail(@RequestParam String username, OAuth2Authentication authentication) {
//        return repository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(User.class, "email", email));
//    }

    @GetMapping("/{id}")
    @PostAuthorize("!hasRole('USER') || (returnObject != null && returnObject.email == authentication.principal)")
    public User one(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User with id: %d not found", id)));
    }

//    @PutMapping("/{id}")
//    @PreAuthorize("!hasAuthority('USER') || (authentication.principal == @userRepository.findById(#id).orElse(new net.reliqs.gleeometer.users.User()).email)")
//    public void update(@PathVariable Long id, @Valid @RequestBody User res) {
//        User u = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User with id: %d not found", id)));
//        res.setPassword(u.getPassword());
//        res.setGlee(u.getGlee());
//        repository.save(res);
//    }
//
//    @PostMapping
//    @PreAuthorize("!hasAuthority('USER')")
//    public User create(@Valid @RequestBody User res) {
//        return userRepository.save(res);
//    }
    // создаем нового пользователя
//    @PostMapping()
//    public UserDto saveNewUser(@RequestBody UserDto userDto) {
//        // создаем нового user и передаем ему параметры нового созданного dto
//        User user = userConverter.dtoToEntity(userDto);
//        // преверяем есть ли он в базе если нет то создаем нового
//          user = userService.findByUsername(user.getUsername()).orElse( user = userService.save(user));
//        // возвращаем dto
//        return userConverter.entityToDto(user);
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("!hasAuthority('USER')")
//    public void delete(@PathVariable Long id) {
//        if (repository.existsById(id)) {
//            repository.deleteById(id);
//        } else {
//            throw new ResourceNotFoundException(String.format("User with id: %d not found", id));
//        }
//    }
//
//    @PutMapping("/{id}/changePassword")
//    @PreAuthorize("!hasAuthority('USER') || (#oldPassword != null && !#oldPassword.isEmpty() && authentication.principal == @userRepository.findById(#id).orElse(new net.reliqs.gleeometer.users.User()).email)")
//    public void changePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
//        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
//        if (oldPassword == null || oldPassword.isEmpty() || passwordEncoder.matches(oldPassword, user.getPassword())) {
//            user.setPassword(passwordEncoder.encode(newPassword));
//            repository.save(user);
//        } else {
//            throw new ConstraintViolationException("old password doesn't match", new HashSet<>());
//        }
//    }
}
