create table if not exists products_product_tags
(
    id    uuid,
    product_id  uuid references products (id),
    product_tag_id uuid references product_tags(id),
    primary key (id)
);
