package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.category.StoreCategoriesDto
import br.com.devLearn.application.controller.dtos.category.CategoriesViewDto
import br.com.devLearn.application.controller.dtos.category.UpdateCategoriesDto
import br.com.devLearn.application.controller.mappers.category.StoreCategoriesMapper
import br.com.devLearn.application.controller.mappers.category.CategoriesViewListMapper
import br.com.devLearn.application.controller.mappers.category.CategoriesViewMapper
import br.com.devLearn.application.controller.mappers.category.UpdateCategoriesMapper
import br.com.devLearn.application.service.CategoriesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/categories")
class CategoriesController(
    private val service: CategoriesService,
    private val updateMapper: UpdateCategoriesMapper,
    private val viewMapper: CategoriesViewMapper,
    private val viewListMapper: CategoriesViewListMapper,
    private val storeMapper: StoreCategoriesMapper
) {

    @GetMapping
    fun listCategory(): List<CategoriesViewDto>{
        return viewListMapper.map(service.listCategories())
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): CategoriesViewDto{
        return viewMapper.map(service.getCategoryById(id))
    }

    @PostMapping
    fun saveCourse(@RequestBody @Valid dto: StoreCategoriesDto,
                   uriBuilder: UriComponentsBuilder):ResponseEntity<CategoriesViewDto>{
        val categoryView = viewMapper.map(service.storeCategory(storeMapper.map(dto)))
        val uri = uriBuilder.path("/category/${categoryView.id}").build().toUri()
        return ResponseEntity.created(uri).body(categoryView)
    }

    @PutMapping("{id}")
    fun updateCategory(@PathVariable id: Long,
                       @RequestBody @Valid category: UpdateCategoriesDto): ResponseEntity<CategoriesViewDto>{
        val categoryView = viewMapper.map(service.storeCategory(updateMapper.map(category, service.getCategoryById(id))))
        return ResponseEntity.ok(categoryView)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable id: Long){
        service.deleteCategory(id)
    }
}