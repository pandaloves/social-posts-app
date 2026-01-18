package se.jensen.grupp9.socialpostsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.jensen.grupp9.socialpostsapp.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on {@link User} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard database operations and includes
 * additional query methods for searching users by username, email, role, display name, and bio.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user.
     * @return An {@link Optional} containing the user if found, or empty if not found.
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by their email address.
     *
     * @param email The email of the user.
     * @return An {@link Optional} containing the user if found, or empty if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists with the specified username.
     *
     * @param username The username to check.
     * @return True if a user with the username exists, false otherwise.
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user exists with the specified email address.
     *
     * @param email The email to check.
     * @return True if a user with the email exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Finds all users whose bio contains the specified keyword (case-sensitive).
     *
     * @param keyword The keyword to search for in bios.
     * @return A list of users whose bio contains the given keyword.
     */
    List<User> findByBioContaining(String keyword);
}
