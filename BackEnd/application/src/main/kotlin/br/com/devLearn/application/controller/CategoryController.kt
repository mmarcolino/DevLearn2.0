package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.category.StoreCategoryDto
import br.com.devLearn.application.controller.dtos.category.CategoryViewDto
import br.com.devLearn.application.controller.dtos.category.UpdateCategoryDto
import br.com.devLearn.application.controller.mappers.category.StoreCategoryMapper
import br.com.devLearn.application.controller.mappers.category.CategoryViewListMapper
import br.com.devLearn.application.controller.mappers.category.CategoryViewMapper
import br.com.devLearn.application.controller.mappers.category.UpdateCategoryMapper
import br.com.devLearn.application.service.CategoryService
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
class CategoryController(
    private val service: CategoryService,
    private val updateMapper: UpdateCategoryMapper,
    private val viewMapper: CategoryViewMapper,
    private val viewListMapper: CategoryViewListMapper,
    private val storeMapper: StoreCategoryMapper
) {

    @GetMapping
    fun listCategory(): List<CategoryViewDto>{
        return viewListMapper.map(service.listCategories())
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): CategoryViewDto{
        return viewMapper.map(service.getCategoryById(id))
    }

    @PostMapping
    fun saveCourse(@RequestBody @Valid dto: StoreCategoryDto,
                   uriBuilder: UriComponentsBuilder):ResponseEntity<CategoryViewDto>{
        val categoryView = viewMapper.map(service.storeCategory(storeMapper.map(dto)))
        val uri = uriBuilder.path("/category/${categoryView.id}").build().toUri()
        return ResponseEntity.created(uri).body(categoryView)
    }

    @PutMapping("{id}")
    fun updateCategory(@PathVariable id: Long,
                       @RequestBody @Valid category: UpdateCategoryDto): ResponseEntity<CategoryViewDto>{
        val categoryView = viewMapper.map(service.storeCategory(updateMapper.map(category, service.getCategoryById(id))))
        return ResponseEntity.ok(categoryView)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable id: Long){
        service.deleteCategory(id)
    }
}