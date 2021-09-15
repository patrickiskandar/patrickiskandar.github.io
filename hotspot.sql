SELECT bname, COUNT(*)
FROM patients
GROUP BY bname
ORDER BY bname;;
