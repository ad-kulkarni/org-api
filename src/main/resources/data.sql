DROP TABLE IF EXISTS organization;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS organization_user;

CREATE TABLE organization (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  phone VARCHAR(15) NOT NULL
);

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  phone VARCHAR(15) NOT NULL
);

CREATE TABLE organization_user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  org_id INT NOT NULL,
  user_id INT NOT NULL,

  FOREIGN KEY (org_id)
    REFERENCES organization(id)
    ON UPDATE CASCADE,

  FOREIGN KEY (user_id)
      REFERENCES user(id)
      ON UPDATE CASCADE
);


--INSERT INTO organization (name, address, phone) VALUES
--  ('Org-1', '123 S Street', '+1234567890'),
--  ('Org-2', '246 N Street', '+19087654321');
--
--INSERT INTO user (first_name, last_name, email, address, phone) VALUES
--  ('user-1', 'l1' , 'asda@asdasd.com', '123 S Street', '+1234567890'),
--  ('user-2', 'l2', 'dfsrs@fwer.com', '123 S Street', '+19087654321'),
--  ('user-3', 'l3', 'sdff@dfgd.com', '123 S Street', '+687685456'),
--  ('user-4', 'l4', 'jghj@yjyt.com', '123 S Street', '+19087654321');
