-- какой тэг сколько раз встречается

select t.tag, COUNT(m.tags_tag_id) from public.hashtag t
                                            join public.message_tags m on t.tag_id = m.tags_tag_id
group by t.tag order by count(m.tags_tag_id) desc