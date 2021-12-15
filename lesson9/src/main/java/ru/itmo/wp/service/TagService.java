package ru.itmo.wp.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    public static List<String> parseTags(String tags) {
        String[] tagsArray = tags.split("\\s+");
        List<String> res = new ArrayList<>();
        for (String tag : tagsArray) {
            if (!tag.isEmpty()) {
                res.add(tag);
            }
        }
        return res;
    }
}