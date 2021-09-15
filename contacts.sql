SELECT name, email_address, phone_num
FROM persons WHERE personid IN
  (SELECT a.personid FROM administer a, patients p
  WHERE p.email_address = 'dub.vizer@br.com')
;;
