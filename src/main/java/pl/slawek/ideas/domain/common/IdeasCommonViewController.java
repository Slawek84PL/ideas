package pl.slawek.ideas.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import pl.slawek.ideas.IdeasConfiguration;
import pl.slawek.ideas.domain.service.CategoryService;

public abstract class IdeasCommonViewController {

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    private IdeasConfiguration ideasConfiguration;

    protected void addGlobalAttribute(Model model) {
        model.addAttribute("categories", categoryService.getCategories(
                PageRequest.of(0, 10, Sort.by("name").ascending())
        ));
    }

    protected PageRequest getPageRequest(int page) {
        return PageRequest.of(page - 1, ideasConfiguration.getPagingPageSize(), Sort.by("name").ascending());
    }
}
