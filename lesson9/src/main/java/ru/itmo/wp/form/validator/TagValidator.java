package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.service.TagService;

import java.util.List;

@Component
public class TagValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return String.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            String tags = (String) target;
            List<String> tagsArray = TagService.parseTags(tags);
            for (String tag : tagsArray) {
                if (tag.length() > 60 || !tag.matches("[a-zA-Z]+")) {
                    errors.reject("tags.invalid-tags", "tags should be latin letters only length less then 60");
                }
            }
        }
    }
}
