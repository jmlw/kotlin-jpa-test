package com.joshmlwood.kotlinJpaTest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.repository.CrudRepository
import javax.persistence.*

@SpringBootApplication
class Application

@Entity
class Nested(
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    val id: Long? = null,
    val data: String? = null
)

interface NestedRepository : CrudRepository<Nested, Long>

@Entity
class Wrapper(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Long? = null,
    val data: String? = null,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "wrapperId")
    val nested: List<Nested>? = null
)

interface WrapperRepository : CrudRepository<Wrapper, Long>

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java)
}