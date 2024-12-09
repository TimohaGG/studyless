package com.studyless.studylesskidscrm.Repos;

import com.studyless.studylesskidscrm.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepos extends JpaRepository<User,Long> {
}
